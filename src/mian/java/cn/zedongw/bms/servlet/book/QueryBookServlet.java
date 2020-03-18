package cn.zedongw.bms.servlet.book;

import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.dao.impl.DaoImpl;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.Users;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName QueryBookServlet
 * @Description 查看书本
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 20:57
 * @modified By：
 */

public class QueryBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if(session != null){
            String id1 = (String)session.getAttribute("id");
            if(id1 != null){
                Dao<Users> usersDao = new DaoImpl<Users>();
                Dao<Books> booksDao = new DaoImpl<Books>();
                String id = req.getParameter("id");
                try {
                    Users user = usersDao.findById(new Users(), id1);
                    Books book = null;
                    if(id != null){
                        book = booksDao.findById(new Books(), id);
                    } else {
                        book = new Books();
                    }
                    req.setAttribute("book", book);
                    req.getRequestDispatcher("queryBook.jsp").forward(req, resp);
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
