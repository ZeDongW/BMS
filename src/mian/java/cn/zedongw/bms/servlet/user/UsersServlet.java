package cn.zedongw.bms.servlet.user;


import cn.zedongw.bms.dao.Dao;
import cn.zedongw.bms.dao.impl.DaoImpl;
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
 * @ClassName UsersServlet
 * @Description 用户管理Servlet
 * @Version 1.0
 * @date ：Created in 2019/5/6 0006 7:05
 * @modified By：
 */

public class UsersServlet extends HttpServlet {

    private static final String ADMIN = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if (session != null) {
            String id = (String) session.getAttribute("id");
            if (id != null) {
                Dao<Users> usersDao = new DaoImpl<Users>();
                try {
                    Users user = usersDao.findById(new Users(), id);
                    ArrayList<Users> list = usersDao.findAll(new Users());
                    String sort = req.getParameter("sort");
                    list = Sort.sort(list, sort);
                    String html = getHtml(req, user, list);
                    resp.getWriter().write(html);
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
                resp.sendRedirect(req.getContextPath() + "/index.html");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

    private String getHtml(HttpServletRequest req, Users user, ArrayList<Users> list) {
        String html = "";
        html += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
        html += "<html xmlns='http://www.w3.org/1999/xhtml'>";
        html += "<head>";
        html += "    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
        html += "    <title>用户管理</title>";
        html += "    <style type='text/css'>";
        html += "        table td{";
        html += "            text-align:center;";
        html += "        }";
        html += "        table{";
        html += "           border-collapse:collapse;";
        html += "        }";
        html += "    </style>";
        html += "</head>";
        html += "<body>";
        html += "<div style='text-align: center'>    欢迎登陆， <font size='+2' color='green'> " + user.getUserName() + " </font><br />";
        html += "<h3>用户管理</h3></div>";
        html += "<table align='center' border='1' width='800px'>";
        html += "    <tr>";
        html += "        <th><a href='" + req.getContextPath() + "/users?sort=" + "userId" + "'>编号</a></th>";
        html += "        <th><a href='" + req.getContextPath() + "/users?sort=" + "userName" + "'>用户名</a></th>";
        if (ADMIN.equals(user.getUserName())) {
            html += "        <th>操作</th>";
            html += "    </tr>";
            if (list != null) {
                for (Users user1 : list) {
                    html += "    <tr>";
                    html += "    	<td>" + user1.getId() + "</td>";
                    html += "    	<td>" + user1.getUserName() + "</td>";
                    html += "        <td><a href='" + req.getContextPath() + "/queryUser?id=" + user1.getId() + "'>修改</a>&nbsp;<a href='" + req.getContextPath() + "/deleteUser?id=" + user1.getId() + "'>删除</a></td>";
                    html += "    </tr>";
                }
            }
        } else {
            html += "    </tr>";
            if (list != null) {
                for (Users user1 : list) {
                    html += "    <tr>";
                    html += "    	<td>" + user1.getId() + "</td>";
                    html += "    	<td>" + user1.getUserName() + "</td>";
                    html += "    </tr>";
                }
            }
        }

        html += "</table>";
        html += " <div style='text-align: center'><a href='" + req.getContextPath() + "/index'>返回主页</a>&nbsp;&nbsp;&nbsp;<a href=' " + req.getContextPath() + "/queryUser?id=" + user.getId() + "'>用户修改</a>&nbsp;&nbsp;&nbsp;<a href=' " + req.getContextPath() + "/logOut'>安全退出</a></div>";
        html += "</body>";
        html += "</html>";
        return html;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
