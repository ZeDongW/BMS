package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.dao.impl.DaoImpl;
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
 * @ClassName UpdateUserServlet
 * @Description 修改用户Servlet
 * @Version 1.0
 * @date ：Created in 2019/5/6 0006 7:45
 * @modified By：
 */

public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if(session != null){
            String id1 = (String)session.getAttribute("id");
            if(id1 != null){
                Dao<Users> usersDao = new DaoImpl<Users>();
                String id = req.getParameter("id");
                String userName = req.getParameter("userName");
                String passWord = req.getParameter("passWord");
                Users user = new Users(id,userName,passWord);
                try {
                    usersDao.update(user, id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect(req.getContextPath() + "/users");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
