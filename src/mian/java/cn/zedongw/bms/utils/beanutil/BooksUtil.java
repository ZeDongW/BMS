package cn.zedongw.bms.utils.beanutil;


import cn.zedongw.bms.entity.Books;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;

/**
 * @Author ZeDongW
 * @ClassName BooksUtil
 * @Description 书本实体封装工具
 * @Version 1.0
 * @date ：Created in 6/28/2019 7:45 AM
 * @modified By：
 */

public class BooksUtil{

    /**
     * @Author: ZeDongW
     * @Description: 从数据库结果集中封装对象
     * @Date: 6/28/2019 8:10 AM
     * @Param: [rs]
     * @return: cn.zedongw.entity.Books
     */
    public static Books getBean(ResultSet rs) {
        Books book = new Books();
        try {
            String id = rs.getString("id");
            String bookName = rs.getString("bookName");
            String bookAuthor = rs.getString("bookAuthor");
            String publisher = rs.getString("publisher");
            double price = rs.getDouble("price");
            int bookNum = rs.getInt("bookNum");
            Date publishDate = rs.getDate("publishDate");
            //BeanUtils封装属性
            getBooksBean(book, id, bookName, bookAuthor, publisher, price, bookNum, publishDate);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    /**
     * @Author: ZeDongW
     * @Description: 从request请求中封装对象
     * @Date: 6/28/2019 8:11 AM
     * @Param: [req]
     * @return: cn.zedongw.entity.Books
     */
    public static Books getBean(HttpServletRequest req, String id) {
        Books book = new Books();
        String bookName = req.getParameter("bookName");
        String bookAuthor = req.getParameter("bookAuthor");
        String publisher = req.getParameter("publisher");
        double price = Double.parseDouble(req.getParameter("price"));
        int bookNum = Integer.parseInt(req.getParameter("bookNum"));
        Date publishDate = Date.valueOf(req.getParameter("publishDate"));

        //BeanUtils封装属性
        try {

            getBooksBean(book, id, bookName, bookAuthor, publisher, price, bookNum, publishDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return book;
    }

    /**
     * @Author: ZeDongW
     * @Description: 通过BeanUtils封装属性
     * @Date: 6/28/2019 8:23 AM
     * @Param: [book, bookName, bookAuthor, publisher, price, bookNum]
     * @return: void
     */
    private static void getBooksBean(Books book, String id, String bookName, String bookAuthor, String publisher, double price, int bookNum, Date publishDate) throws IllegalAccessException, InvocationTargetException {

        //注册日期类格式转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);

        if (id != null){
            BeanUtils.copyProperty(book, "id", id);
        }

        BeanUtils.copyProperty(book, "bookName", bookName);
        BeanUtils.copyProperty(book, "bookAuthor", bookAuthor);
        BeanUtils.copyProperty(book, "publisher", publisher);
        BeanUtils.copyProperty(book, "price", price);
        BeanUtils.copyProperty(book, "bookNum", bookNum);
        BeanUtils.copyProperty(book, "publishDate", publishDate);
    }
}
