package cn.zedongw.bms.servlet;

import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.dao.impl.DaoImpl;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.Dom4jUtils;
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
 * @ClassName LoginServlet
 * @Description 登录
 * @Version 1.0
 * @date ：Created in 2019/5/4 0004 21:07
 * @modified By：
 */

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String uuid = Dom4jUtils.getUuid();
        Users user = new Users(uuid, userName, passWord);
        Dao<Users> usersDao = new DaoImpl<Users>();
        try {
            ArrayList<Users> usersList = usersDao.findAll(new Users());
            if(usersList.contains(user)){
                for (Users user1 : usersList) {
                    if(user1.getUserName().equals(userName)){
                        if(user1.getPassWord().equals(passWord)){
                            HttpSession session = req.getSession();
                            session.setAttribute("id",user1.getId());
                            resp.sendRedirect(req.getContextPath() + "/index");
                        } else {
                            resp.getWriter().write("密码错误，请输入正确密码，3秒后返回注册页面");
                            resp.setHeader("refresh","3;url= " + req.getContextPath() + "/login.jsp”");
                            return;
                        }
                    }
                }
            } else {
                resp.getWriter().write("该用户名不存在，请输入正确用户名，3秒后返回注册页面");
                resp.setHeader("refresh","3;url= " + req.getContextPath() + "/login.jsp”");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
