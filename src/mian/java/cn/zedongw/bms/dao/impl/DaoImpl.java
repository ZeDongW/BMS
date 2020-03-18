package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.utils.Dom4jUtils;
import org.dom4j.DocumentException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName DaoImpl
 * @Description 操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/5/4 0004 22:44
 * @modified By：
 */

public class DaoImpl <T> implements Dao<T> {

    /**
     * @Author: ZeDongW
     * @Description: 添加
     * @Date: 2019/5/5 0005 6:34
     * @Param: [u]
     * @return: void
     */
    @Override
    public void add(T t, String id) throws DocumentException, IllegalAccessException {
        Dom4jUtils.addElement(t, id);
    }

    /**
     * @Author: ZeDongW
     * @Description: 删除
     * @Date: 2019/5/5 0005 6:35
     * @Param: [u]
     * @return: void
     */
    @Override
    public void delete(T t, String id) throws DocumentException, IllegalAccessException {
        Dom4jUtils.removeElement(t.getClass(), id);
    }

    /**
     * @Author: ZeDongW
     * @Description: 修改
     * @Date: 2019/5/5 0005 7:53
     * @Param: [t, id]
     * @return: void
     */
    @Override
    public void update(T t, String id) throws IllegalAccessException, DocumentException {
        Dom4jUtils.updateElement(t, id);
    }

    /**
     * @Author: ZeDongW
     * @Description: 查找所有
     * @Date: 2019/5/5 0005 7:53
     * @Param: []
     * @return: java.util.ArrayList<T>
     */
    @Override
    public ArrayList<T> findAll(T t) throws ParseException, InstantiationException, DocumentException, IllegalAccessException {
        return (ArrayList<T>) Dom4jUtils.findAll(t.getClass());
    }

    /**
     * @Author: ZeDongW
     * @Description: 根据id查找
     * @Date: 2019/5/5 0005 7:54
     * @Param: [id]
     * @return: T
     */
    @Override
    public T findById(T t,String id) throws DocumentException, IllegalAccessException, InstantiationException {
        return (T) Dom4jUtils.findElementById(t.getClass(), id);
    }
}
