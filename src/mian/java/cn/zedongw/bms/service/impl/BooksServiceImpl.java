package cn.zedongw.bms.service.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.dao.impl.BooksDaoImpl;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.service.IBooksService;

/**
 * @Author ZeDongW
 * @ClassName BooksServiceImpl
 * @Description 书本业务逻辑层接口实现
 * @Version 1.0
 * @date ：Created in 7/3/2019 6:33 AM
 * @modified By：
 */

public class BooksServiceImpl implements IBooksService {

    /**
     * 实例化数据访问层
     */
    IBooksDao dao = new BooksDaoImpl();

    @Override
    public void addBooks(Books book) {
        dao.add(book);
    }

    @Override
    public void deleteBooks(String id) {
        dao.delete(id);
    }

    @Override
    public void updateBooks(Books book) {
        dao.update(book);
    }

    @Override
    public Books findBooksById(String id) {
        return dao.findById(id);
    }

    @Override
    public void findAllBooks(PageBean<Books> pb) {
        dao.findAll(pb);
    }
}
