package cn.zedongw.bms.dao;

import cn.zedongw.bms.entity.Books;

import java.util.ArrayList;

/**
 * @InterfaceName IBooksDao
 * @Description: 书本持久层接口
 * @Author ZeDongW
 * @Date 2020/3/28 0028 19:49
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public interface IBooksDao {

    /**
     * 功能描述: 新增书本
     * @methodName: add
     * @param book 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:50
     */
    public void add(Books book);

    /**
     * 功能描述: 根据ID删除书本
     * @methodName: delete
     * @param id 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:50
     */
    public void delete(String id);

    /**
     * 功能描述: 更新书本
     * @methodName: update
     * @param book 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:51
     */
    public void update(Books book);

    /**
     * 功能描述: 查找所有书本
     * @methodName: findAll
     * @throws
     * @return: java.util.ArrayList<cn.zedongw.bms.entity.Books>
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:51
     */
    public ArrayList<Books> findAll();

    /**
     * 功能描述: 根据ID查找书本
     * @methodName: findById
     * @param id 1
     * @throws
     * @return: cn.zedongw.bms.entity.Books
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:51
     */
    public Books findById(String id);
}
