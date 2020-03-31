package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.BaseDao;
import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author ZeDongW
 * @ClassName BooksDaoImpl
 * @Description 书本数据操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:16
 * @modified By：
 */

public class BooksDaoImpl extends BaseDao implements IBooksDao {

    @Override
    public void add(Books book) {
        //SQL
        String sql = "insert into books (id, bookName, bookAuthor, publisher, price, bookNum, publishDate) values (?, ?, ?, ?, ?, ?, ?)";

        //调用父类更新方法
        super.update(sql, new Object[]{book.getId(), book.getBookName(), book.getBookAuthor(), book.getPublisher(),
                book.getPrice(), book.getBookNum(), book.getPublishDate()});
    }

    @Override
    public void delete(String id) {
        //SQL
        String sql = "delete from books where id = ?";

        //调用父类更新方法
        super.update(sql, new Object[]{id});
    }

    @Override
    public void update(Books book) {
        //SQL
        String sql = "update books set bookName = ?, bookAuthor = ?, publisher = ?, price = ?, " +
                "bookNum = ?, publishDate = ? where id = ?";

        //调用父类更新方法
        super.update(sql, new Object[]{book.getBookName(), book.getBookAuthor(), book.getPublisher(),
                book.getPrice(), book.getBookNum(), book.getPublishDate(), book.getId()});
    }

    @Override
    public ArrayList<Books> findAll() {
        //SQL
        String sql = "select * from books";

        //调用父类的查询方法
        ArrayList<Books> booksList = super.query(sql, null, Books.class);

        return booksList;
    }


    @Override
    public Books findById(String id) {
        //SQL
        String sql = "select * from books where id = ?";

        //调用父类的查询方法
        ArrayList<Books> booksList = super.query(sql, new Object[]{id}, Books.class);

        //获取迭代器
        Iterator<Books> iterator = booksList.iterator();
        while (iterator.hasNext()) {
            return iterator.next();
        }

        return null;
    }

}
