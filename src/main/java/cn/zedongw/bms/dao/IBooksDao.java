package cn.zedongw.bms.dao;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;

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
     * Description: 分页查找所有书本
     *
     * @param pb 1
     * @throws
     * @methodName: findAll
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/30 0030 13:17
     */
    void findAll(PageBean<Books> pb);

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

    /**
     * Description: 获取总记录数
     *
     * @param
     * @throws
     * @methodName: getTotalCount
     * @return: int
     * @author: ZeDongW
     * @date: 2020/3/30 0030 13:18
     */
    int getTotalCount();
}
