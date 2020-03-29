package cn.zedongw.bms.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
        String path = name + ".xml";

        return new SAXReader().read(Dom4jUtils.class.getClassLoader().getResourceAsStream(path));
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
        String path = name + ".xml";
        if(dom == null){
            dom = DocumentHelper.createDocument();
            Element element = dom.addElement(name);
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(Objects.requireNonNull(Dom4jUtils.class.getClassLoader().getResource(path)).getFile()));
            OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
            prettyPrint.setEncoding("UTF-8");
            XMLWriter xmlWriter = new XMLWriter(bufferedWriter, prettyPrint);
            xmlWriter.write(dom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
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
    public static <T> void addElement(T t, String id) throws IllegalAccessException, DocumentException {
        Class clazz = t.getClass();
        className = clazz.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        dom = loadXml(clazz);
        rootElement = dom.getRootElement();
        Element element = rootElement.addElement(name.substring(0,name.length()-1));
        element.addAttribute("id",id);
        getElement(t, clazz, element);
        writeXml(clazz, dom);
    }

    /**
     * @Author: ZeDongW
     * @Description: 移除Xml对象
     * @Date: 2019/4/20 0020 10:22
     * @Param: [t, dom]
     * @return: void
     */
    public static <T> void removeElement(Class<T> obj, String id) throws IllegalAccessException, DocumentException {
        className = obj.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        Field[] declaredFields = obj.getDeclaredFields();
        xPath = "//"+name.substring(0,name.length()-1)+"[@id='" + id + "']";
        dom = loadXml(obj);
        Element element = (Element)dom.selectSingleNode(xPath);
        if(element != null){
            element.detach();
        }
        writeXml(obj, dom);
    }

    /**
     * @Author: ZeDongW
     * @Description: 根据ID修改元素
     * @Date: 2019/5/5 0005 7:03
     * @Param: [obj, id]
     * @return: void
     */
    public static <T> void updateElement(T t, String id) throws DocumentException, IllegalAccessException {
        Class clazz = t.getClass();
        className = clazz.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        Field[] declaredFields = clazz.getDeclaredFields();
        xPath = "//"+name.substring(0,name.length()-1)+"[@id='" + id + "']";
        dom = loadXml(clazz);
        Element element = (Element)dom.selectSingleNode(xPath);
        getElement(t, clazz, element);
        writeXml(clazz, dom);
    }

    /**
     * @Author: ZeDongW
     * @Description: 解析文档，将文档中的内容封装为对象，以list返回
     * @Date: 2019/4/19 0019 19:31
     * @Param: [obj]
     * @return: java.util.ArrayList<T>
     */
    public static <T> ArrayList<T>  findAll(Class<T> obj) throws DocumentException, IllegalAccessException, InstantiationException, ParseException {
        ArrayList<T> lists = new ArrayList<>();
        dom = loadXml(obj);
        rootElement= dom.getRootElement();
        xPath = rootElement.getName() + "/*";
        List<Element> list = dom.selectNodes(xPath);
        for (Element element : list) {
            T t = getObj(obj, element);
            lists.add(t);
        }
        return lists;
    }

    /**
     * @Author: ZeDongW
     * @Description: 跟根据id查找元素，并返回
     * @Date: 2019/5/5 0005 6:43
     * @Param: [t, id]
     * @return: T
     */
    public static <T> T findElementById(Class<T> obj, String id) throws DocumentException, IllegalAccessException, InstantiationException {
        className = obj.getName();
        name = className.substring(className.lastIndexOf(".") + 1);
        Field[] declaredFields = obj.getDeclaredFields();
        xPath = "//"+name.substring(0,name.length()-1)+"[@id='" + id + "']";
        dom = loadXml(obj);
        Element element = (Element)dom.selectSingleNode(xPath);
        if(element == null){
            return null;
        }
        return getObj(obj, element);
    }

    /**
     * @Author: ZeDongW
     * @Description: 获取UUID
     * @Date: 2019/5/5 0005 6:54
     * @Param: []
     * @return: java.lang.String
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * @Author: ZeDongW
     * @Description: 将元素封装为对象
     * @Date: 2019/5/5 0005 6:55
     * @Param: [obj, element]
     * @return: T
     */
    private static <T> T getObj(Class<T> obj, Element element) throws InstantiationException, IllegalAccessException {
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
            } else {
                field.set(t,value);
            }
        }
        return t;
    }

    /**
     * @Author: ZeDongW
     * @Description: 将对象封装为element
     * @Date: 2019/5/5 0005 7:09
     * @Param: [t, clazz, element]
     * @return: void
     */
    private static <T> void getElement(T t, Class clazz, Element element) throws IllegalAccessException {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String field = declaredField.getName();
            Element element1 = element.element(field);
            if (element1 !=null){
                element1.detach();
            }
            Element ele = element.addElement(field);
            Class<?> type = declaredField.getType();
            ele.setText(String.valueOf(declaredField.get(t)));
        }
    }
}
