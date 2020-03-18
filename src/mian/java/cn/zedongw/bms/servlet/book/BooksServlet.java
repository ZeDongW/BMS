package cn.zedongw.bms.servlet.book;

import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.dao.impl.DaoImpl;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.comparator.Sort;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName BooksServlet
 * @Description 图书servlet
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 20:03
 * @modified By：
 */

public class BooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if(session != null){
            String id = (String)session.getAttribute("id");
            if(id != null){
                Dao<Users> usersDao = new DaoImpl<Users>();
                Dao<Books> booksDao = new DaoImpl<Books>();
                try {
                    Users user = usersDao.findById(new Users(), id);
                    ArrayList<Books> list = booksDao.findAll(new Books());
                    String sort = req.getParameter("sort");
                    list = Sort.sort(list,sort);
                    req.setAttribute("list", list);
                    req.getRequestDispatcher("/books.jsp").forward(req, resp);
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
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
        doGet(req,resp);
    }
}
