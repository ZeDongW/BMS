package cn.zedongw.bms.service.impl;

import cn.zedongw.bms.dao.IBooksDao;
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
    private IBooksDao booksDao;

    public void setBooksDao(IBooksDao booksDao) {
        this.booksDao = booksDao;
    }

    @Override
    public void addBooks(Books book) {
        booksDao.add(book);
    }

    @Override
    public void deleteBooks(String id) {
        booksDao.delete(id);
    }

    @Override
    public void updateBooks(Books book) {
        booksDao.update(book);
    }

    @Override
    public Books findBooksById(String id) {
        return booksDao.findById(id);
    }

    @Override
    public void findAllBooks(PageBean<Books> pb) {
        booksDao.findAll(pb);
    }
}
