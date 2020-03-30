package cn.zedongw.bms.service;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;

/**
 * @InterfaceName IBooksService
 * @Description: 书本业务逻辑层接口
 * @Author ZeDongW
 * @Date 2020/3/28 0028 19:41
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public interface IBooksService {

    /**
     * 功能描述: 添加书本
     * @methodName: addBooks
     * @param book 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:42
     */
    void addBooks(Books book);

    /**
     * 功能描述: 删除书本
     * @methodName: deleteBooks
     * @param id 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:42
     */
    void deleteBooks(String id);

    /**
     * 功能描述: 修改书本
     * @methodName: updateBooks
     * @param book 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:42
     */
    void updateBooks(Books book);

    /**
     * 功能描述: 根据ID查找书本
     * @methodName: findBooksById
     * @param id 1
     * @throws
     * @return: cn.zedongw.bms.entity.Books
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:42
     */
    Books findBooksById(String id);

    /**
     * Description: 分页查找所有书本
     *
     * @param pb 1
     * @throws
     * @methodName: findAllBooks
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/30 0030 14:01
     */
    void findAllBooks(PageBean<Books> pb);

}
