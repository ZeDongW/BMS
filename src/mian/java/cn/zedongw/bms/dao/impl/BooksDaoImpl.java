package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.utils.DbUtils;
import cn.zedongw.bms.utils.beanutil.BooksUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    private Connection conn;
    private String sql;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public void add(Books book) {
        try {
            conn = DbUtils.getConnection();
            sql = "insert into books (id, bookName, bookAuthor, publisher, price, bookNum, publishDate) values (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getId());
            pstmt.setString(2, book.getBookName());
            pstmt.setString(3, book.getBookAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setDouble(5, book.getPrice());
            pstmt.setInt(6, book.getBookNum());
            pstmt.setString(7, book.getPublishDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public void delete(String id) {
        try {
            conn = DbUtils.getConnection();
            sql = "delete from  books where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public void update(Books book) {
        try {
            conn = DbUtils.getConnection();
            sql = "update books set bookName = ?, bookAuthor = ?, publisher = ?, price = ?, bookNum = ?, publishDate = ? where id = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getBookAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setDouble(4, book.getPrice());
            pstmt.setInt(5, book.getBookNum());
            pstmt.setString(6, book.getPublishDate());
            pstmt.setString(7, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public ArrayList<Books> findAll() {
        ArrayList<Books> list = new ArrayList<Books>();
        try {
            conn = DbUtils.getConnection();
            sql = "select * from books";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                list.add(BooksUtil.getBean(rs));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }


    @Override
    public Books findById(String id) {
        try {
            conn = DbUtils.getConnection();
            sql = "select * from books where id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return BooksUtil.getBean(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

}
