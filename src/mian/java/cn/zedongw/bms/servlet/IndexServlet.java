package cn.zedongw.bms.servlet;

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
 * @ClassName IndexServlet
 * @Description 用户功能菜单
 * @Version 1.0
 * @date ：Created in 2019/5/5 0005 7:56
 * @modified By：
 */

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if(session != null){
            String id = (String)session.getAttribute("id");
            if(id != null){
                Dao<Users> usersDao = new DaoImpl<Users>();
                try {
                    Users user = usersDao.findById(new Users(), id);
                    String html = "";
                    html +="<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
                    html +="<html xmlns='http://www.w3.org/1999/xhtml'>";
                    html +="<head>";
                    html +="    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
                    html +="    <title>登录成功</title>";
                    html +="</head>";
                    html +="<center>";
                    html +="    欢迎登陆， <font size='+2' color='green'> " + user.getUserName() + " </font><br />";
                    html +="    <a href=' " + req.getContextPath() + "/users'>用户管理</a> <a href=' " + req.getContextPath() + "/books'>书本管理</a> <br />";
                    html +="    <a href=' " + req.getContextPath() + "/queryUser?id="+ user.getId()+"'>用户修改</a> <a href=' " + req.getContextPath() + "/logOut'>安全退出</a>";
                    html +="</center>";
                    html +="<body>";
                    html +="</body>";
                    html +="</html>";
                    resp.getWriter().write(html);
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
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
