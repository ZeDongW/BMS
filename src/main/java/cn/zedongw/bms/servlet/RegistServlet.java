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
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName RegistServlet
 * @Description 注册
 * @Version 1.0
 * @date ：Created in 2019/5/4 0004 21:07
 * @modified By：
 */

public class RegistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String uuid = Dom4jUtils.getUuid();
        Users user = new Users(uuid, userName, passWord);
        Dao<Users> usersDao = new DaoImpl<>();
        try {
            ArrayList<Users> usersList = usersDao.findAll(new Users());
            if (usersList.contains(user)) {
                resp.getWriter().write("该用户名已被注册，请重新注册，3秒后返回注册页面");
                resp.setHeader("refresh", "3;url= " + req.getContextPath() + "/index.html”");
            } else {
                usersDao.add(user, uuid);
                resp.getWriter().write("注册成功，3秒后返回登录页面");
                resp.setHeader("refresh", "3;url= " + req.getContextPath() + "/index.html”");
            }
        } catch (ParseException | InstantiationException | DocumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
