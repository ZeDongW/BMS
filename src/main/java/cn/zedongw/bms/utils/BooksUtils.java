package cn.zedongw.bms.utils;

import cn.zedongw.bms.entity.Books;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author ZeDongW
 * @ClassName BooksUtils
 * @Description 书本操作工具
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:55
 * @modified By：
 */

public class BooksUtils {
    /**
     * @Author: ZeDongW
     * @Description: 将结果封装为对象
     * @Date: 2019/6/2 0002 20:38
     * @Param: [book, rs]
     * @return: void
     */
    public static void getBook(Books book, ResultSet rs) throws SQLException {
        book.setId(rs.getString("id"));
        book.setBookName(rs.getString("bookName"));
        book.setBookAuthor(rs.getString("bookAuthor"));
        book.setPublisher(rs.getString("publisher"));
        book.setPrice(rs.getDouble("price"));
        book.setBookNum(rs.getInt("bookNum"));
        book.setPublishDate(rs.getString("publishDate"));
    }

    /**
     * @Author: ZeDongW
     * @Description: 将请求封装为对象
     * @Date: 2019/6/2 0002 20:51
     * @Param: [req, id]
     * @return: cn.zedongw.entity.Books
     */
    public static Books setBook(HttpServletRequest req, String id) {
        String bookName = req.getParameter("bookName");
        String bookAuthor = req.getParameter("bookAuthor");
        String publisher = req.getParameter("publisher");
        String price = req.getParameter("price");
        String bookNum = req.getParameter("bookNum");
        String publishDate = req.getParameter("publishDate");
        return new Books(id, bookName, bookAuthor, publisher, Double.valueOf(price), Integer.valueOf(bookNum), publishDate);
    }
}
