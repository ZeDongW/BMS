package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
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
     * 数据库连接对象
     */
    private Connection conn;

    /**
     * sql
     */
    private String sql;

    @Override
    public void add(Books book) {
        try {
            //SQL
            sql = "insert into books (id, bookName, bookAuthor, publisher, price, bookNum, publishDate) values (?, ?, ?, ?, ?, ?, ?)";

            //获取数据库连接对象
            conn = JdbcUtils.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行更新
            queryRunner.update(conn, sql, book.getId(), book.getBookName(), book.getBookAuthor(), book.getPublisher(), book.getPrice(),
                    book.getBookNum(), book.getPublishDate());

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.dbClose(conn);
        }
    }

    @Override
    public void delete(String id) {
        try {
            //SQL
            sql = "delete from books where id = ?";

            //获取数据库连接对象
            conn = JdbcUtils.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行更新
            queryRunner.update(conn, sql, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.dbClose(conn);
        }
    }

    @Override
    public void update(Books book) {
        try {
            //SQL
            sql = "update books set bookName = ?, bookAuthor = ?, publisher = ?, price = ?, " +
                    "bookNum = ?, publishDate = ? where id = ?";

            //获取数据库连接对象
            conn = JdbcUtils.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行更新
            queryRunner.update(conn, sql, book.getBookName(), book.getBookAuthor(), book.getPublisher(),
                    book.getPrice(), book.getBookNum(), book.getPublishDate(), book.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.dbClose(conn);
        }
    }

    @Override
    public ArrayList<Books> findAll() {

        try {
            //SQL
            sql = "select * from books";

            //获取数据库连接对象
            conn = JdbcUtils.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行查询
            return (ArrayList<Books>) queryRunner.query(conn, sql, new BeanListHandler<>(Books.class));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.dbClose(conn);
        }
    }


    @Override
    public Books findById(String id) {

        try {
            //SQL
            sql = "select * from books where id = ?";

            //获取数据库连接对象
            conn = JdbcUtils.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行查询
            return queryRunner.query(conn, sql, new BeanHandler<>(Books.class), id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.dbClose(conn);
        }
    }
}
