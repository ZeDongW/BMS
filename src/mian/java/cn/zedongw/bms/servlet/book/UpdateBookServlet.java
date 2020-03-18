package cn.zedongw.bms.servlet.book;

import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.dao.impl.DaoImpl;
import cn.zedongw.bms.entity.Books;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName UpdateBookServlet
 * @Description 修改书本信息
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 21:35
 * @modified By：
 */

public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if(session != null){
            String id1 = (String)session.getAttribute("id");
            if(id1 != null){
                Dao<Books> booksDao = new DaoImpl<Books>();
                String id = req.getParameter("id");
                String bookName = req.getParameter("bookName");
                String bookAuthor = req.getParameter("bookAuthor");
                String publisher = req.getParameter("publisher");
                String price = req.getParameter("price");
                String bookNum = req.getParameter("bookNum");
                String publishDate = req.getParameter("publishDate");
                Books book = new Books(id, bookName, bookAuthor, publisher, Double.valueOf(price), Integer.valueOf(bookNum), publishDate);
                try {
                    booksDao.update(book, id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
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
