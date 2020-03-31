package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName BooksDaoImpl
 * @Description 书本数据操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:16
 * @modified By：
 */

public class BooksDaoImpl implements IBooksDao {

    /**
     * sql
     */
    private String sql;

    @Override
    public void add(Books book) {
        try {
            //SQL
            sql = "insert into books (id, bookName, bookAuthor, publisher, price, bookNum, publishDate) values (?, ?, ?, ?, ?, ?, ?)";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行更新
            queryRunner.update(sql, book.getId(), book.getBookName(), book.getBookAuthor(), book.getPublisher(), book.getPrice(),
                    book.getBookNum(), book.getPublishDate());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            //SQL
            sql = "delete from books where id = ?";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行更新
            queryRunner.update(sql, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Books book) {
        try {
            //SQL
            sql = "update books set bookName = ?, bookAuthor = ?, publisher = ?, price = ?, " +
                    "bookNum = ?, publishDate = ? where id = ?";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行更新
            queryRunner.update(sql, book.getBookName(), book.getBookAuthor(), book.getPublisher(),
                    book.getPrice(), book.getBookNum(), book.getPublishDate(), book.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Books> findAll() {

        try {
            //SQL
            sql = "select * from books";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行查询
            return (ArrayList<Books>) queryRunner.query(sql, new BeanListHandler<>(Books.class));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Books findById(String id) {

        try {
            //SQL
            sql = "select * from books where id = ?";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行查询
            return queryRunner.query(sql, new BeanHandler<>(Books.class), id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
