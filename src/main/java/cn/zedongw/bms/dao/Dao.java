package cn.zedongw.bms.dao;

import org.dom4j.DocumentException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @Description 数据访问层接口
 * @Version 1.0
 * @date ：Created in 2019/5/4 0004 22:44
 * @modified By：
 */
public interface Dao <T> {
    /**
     * 功能描述: 添加
     * @methodName: add
     * @param t 1
     * @param id 2
     * @throws DocumentException
     * @throws IllegalAccessException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 12:17
     */
    void add(T t, String id) throws DocumentException, IllegalAccessException;

    /**
     * 功能描述: 删除
     * @methodName: delete
     * @param t 1
     * @param id 2
     * @throws DocumentException
     * @throws IllegalAccessException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 12:17
     */
    void delete(T t, String id) throws DocumentException, IllegalAccessException;

    /**
     * 功能描述: 更新
     * @methodName: update
     * @param t 1
     * @param id 2
     * @throws IllegalAccessException
     * @throws DocumentException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 12:36
     */
    void update(T t, String id) throws IllegalAccessException, DocumentException;

    /**
     * 功能描述: 修改
     * @methodName: findAll
     * @param t 1
     * @throws ParseException
     * @throws InstantiationException
     * @throws DocumentException
     * @throws IllegalAccessException
     * @return: java.util.ArrayList<T>
     * @author: ZeDongW
     * @date: 2020/3/18 0018 12:19
     */
    ArrayList<T> findAll(T t) throws ParseException, InstantiationException, DocumentException, IllegalAccessException;

    /**
     * 功能描述: 根据id查找
     * @methodName: findById
     * @param t 1
     * @param id 2
     * @throws DocumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @return: T
     * @author: ZeDongW
     * @date: 2020/3/18 0018 12:20
     */
    T findById(T t, String id) throws DocumentException, IllegalAccessException, InstantiationException;
}
