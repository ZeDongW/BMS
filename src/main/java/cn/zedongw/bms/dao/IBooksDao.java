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
     *
     * @param book 1
     * @throws
     * @methodName: add
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:50
     */
    void add(Books book);

    /**
     * 功能描述: 根据ID删除书本
     *
     * @param id 1
     * @throws
     * @methodName: delete
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:50
     */
    void delete(String id);

    /**
     * 功能描述: 更新书本
     *
     * @param book 1
     * @throws
     * @methodName: update
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:51
     */
    void update(Books book);

    /**
     * 功能描述: 查找所有书本
     *
     * @throws
     * @methodName: findAll
     * @return: java.util.ArrayList<cn.zedongw.bms.entity.Books>
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:51
     */
    ArrayList<Books> findAll();

    /**
     * 功能描述: 根据ID查找书本
     *
     * @param id 1
     * @throws
     * @methodName: findById
     * @return: cn.zedongw.bms.entity.Books
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:51
     */
    Books findById(String id);
}
