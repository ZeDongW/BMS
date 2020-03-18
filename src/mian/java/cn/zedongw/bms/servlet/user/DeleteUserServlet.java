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
 * @ClassName DeleteUserServlet
 * @Description 删除用户
 * @Version 1.0
 * @date ：Created in 2019/5/6 0006 7:34
 * @modified By：
 */

public class DeleteUserServlet extends HttpServlet {
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
                if(id != null){
                    try {
                        usersDao.delete(new Users(), id);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/users");
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
