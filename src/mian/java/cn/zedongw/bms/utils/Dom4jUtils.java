package cn.zedongw.bms.utils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author ZeDongW
 * @ClassName Dom4jUtils
 * @Description Dom4j工具用于读写Xml
 * @Version 1.0
 * @date ：Created in 2019/4/19 0019 7:35
 * @modified By：
 */


public class Dom4jUtils {

    private static String className = null;
    private static String name = null;
    private static String path = null;
    private static Document dom = null;
    private static Element rootElement = null;
    private static String xPath = null;

    /**
     * @Author: ZeDongW
     * @Description: 获取Xml文档
     * @Date: 2019/4/19 0019 7:42
     * @Param: []
     * @return: org.dom4j.Document
     */
    public static <T> Document loadXml(Class<T> obj) throws DocumentException {
        className = obj.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        path = name + ".xml";
        return new SAXReader().read(Dom4jUtils.class.getClassLoader().getResourceAsStream(path));
    }

    /**
     * @Author: ZeDongW
     * @Description: 解析文档，将文档中的内容封装为对象，以list返回
     * @Date: 2019/4/19 0019 19:31
     * @Param: [obj]
     * @return: java.util.ArrayList<T>
     */
    public static <T> ArrayList<T>  getList(Class<T> obj) throws DocumentException, IllegalAccessException, InstantiationException, ParseException {
        ArrayList<T> lists = new ArrayList<T>();
        dom = loadXml(obj);
        rootElement= dom.getRootElement();
        xPath = rootElement.getName() + "/*";
        List<Element> list = dom.selectNodes(xPath);
        for (Element element : list) {
            T t = obj.newInstance();
            Field[] fields = obj.getDeclaredFields();
            List<Element> elements = element.elements();
            for(int i = 0; i < elements.size(); i++){
                Field field = fields[i];
                Class<?> type = field.getType();
                field.setAccessible(true);
                String value = elements.get(i).getText();
                if(double.class.equals(type)){
                    field.set(t,Double.parseDouble(value));
                } else if(int.class.equals(type)) {
                    field.set(t,Integer.parseInt(value));
                } else if(Date.class.equals(type)){
                    field.set(t,MyDateUtils.strToDate(value));
                } else {
                    field.set(t,value);
                }
            }
            lists.add(t);
        }
        return lists;
    }

    /**
     * @Author: ZeDongW
     * @Description: 将dom写入xml中
     * @Date: 2019/4/20 0020 6:40
     * @Param: [obj, dom]
     * @return: void
     */ 
    public static <T> void writeXml (Class<T> obj, Document dom){
        className = obj.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        path = name + ".xml";
        if(dom == null){
            dom = DocumentHelper.createDocument();
            Element element = dom.addElement(name);
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(Dom4jUtils.class.getClassLoader().getResource(path).getFile())));
            OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
            prettyPrint.setEncoding("UTF-8");
            XMLWriter xmlWriter = new XMLWriter(bufferedWriter, prettyPrint);
            xmlWriter.write(dom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    /**
     * @Author: ZeDongW
     * @Description: 增加Xml对象
     * @Date: 2019/4/20 0020 6:43
     * @Param: [obj, dom]
     * @return: void
     */ 
    public static <T> void addElement(T t, Document dom) throws IllegalAccessException {
        Class clazz = t.getClass();
        className = clazz.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        rootElement = dom.getRootElement();
        Element element = rootElement.addElement(name);
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String field = declaredField.getName();
            Element ele = element.addElement(field);
            Class<?> type = declaredField.getType();
            if(Date.class.equals(type)){
                ele.setText(MyDateUtils.dateToStr((Date)declaredField.get(t)));
            } else {
                ele.setText(String.valueOf(declaredField.get(t)));
            }
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 移除Xml对象
     * @Date: 2019/4/20 0020 10:22
     * @Param: [t, dom]
     * @return: void
     */
    public static <T> void removeElement(T t, Document dom) throws IllegalAccessException {
        Class clazz = t.getClass();
        className = clazz.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        Field[] declaredFields = clazz.getDeclaredFields();
        xPath = "//name";
        List<Element> list = dom.selectNodes(xPath);
        boolean flag = true;
        for (Element element : list) {
            List<Element> elements = element.elements();
            for(int i = 0; i < declaredFields.length; i ++){
                Field field = declaredFields[i];
                Class<?> type = field.getType();
                String value = null;
                if(Date.class.equals(type)){
                    value = MyDateUtils.dateToStr((Date)field.get(t));
                } else {
                    value = String.valueOf(field.get(t));
                }
                if(value.equals(elements.get(i).getText())){
                    flag = false;
                    break;
                }
            }
            if(flag){
                element.detach();
            }
        }
    }
}
