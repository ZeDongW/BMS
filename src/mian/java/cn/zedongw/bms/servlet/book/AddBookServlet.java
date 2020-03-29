package cn.zedongw.bms.servlet.book;

import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.dao.impl.DaoImpl;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.utils.Dom4jUtils;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName AddBookServlet
 * @Description 添加书本
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 23:10
 * @modified By：
 */

public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if(session != null){
            String id1 = (String)session.getAttribute("id");
            if(id1 != null){
                Dao<Books> booksDao = new DaoImpl<>();
                String id = Dom4jUtils.getUuid();
                String bookName = req.getParameter("bookName");
                String bookAuthor = req.getParameter("bookAuthor");
                String publisher = req.getParameter("publisher");
                String price = req.getParameter("price");
                String bookNum = req.getParameter("bookNum");
                String publishDate = req.getParameter("publishDate");
                Books book = new Books(id, bookName, bookAuthor, publisher, Double.parseDouble(price), Integer.parseInt(bookNum), publishDate);
                try {
                    booksDao.add(book, id);
                } catch (DocumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect(req.getContextPath() + "/books");
            } else {
                resp.sendRedirect(req.getContextPath() + "/index.html");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
