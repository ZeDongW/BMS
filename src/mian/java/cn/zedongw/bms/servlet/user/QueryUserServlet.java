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
 * @ClassName QueryUserServlet
 * @Description 查询用户
 * @Version 1.0
 * @date ：Created in 2019/5/11 0011 16:23
 * @modified By：
 */

public class QueryUserServlet extends HttpServlet {
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
                try {
                    Users user1 = usersDao.findById(new Users(), id1);
                    Users user = usersDao.findById(new Users(), id);
                    String html = "";
                    html += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
                    html += "<html xmlns='http://www.w3.org/1999/xhtml'>";
                    html += "<head>";
                    html += "    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
                    html += "    <title>修改用户</title>";
                    html += "</head>";
                    html += "";
                    html += "<body>";
                    html += "<center>欢迎登陆， <font size='+2' color='green'> " + user1.getUserName() + " </font><br />";
                    html += "<h3>修改用户</h3></center>";
                    html += "<form action=' " + req.getContextPath() + "/updateUser' method='post' onsubmit='return checkForm()'>";
                    html += "    <input type='hidden' name='id' value='" + user.getId() + "'/>";
                    html += "    <table align='center' border='1' width='300px'>";
                    html += "        <tr>";
                    html += "            <th>用户名</th>";
                    html += "            <td><input type='text' name='userName' value='"+ user.getUserName()+"' onblur='checkUserName()' id='userName'/><span id='userNameCheck'></span></td>";
                    html += "        </tr>";
                    html += "        <tr>";
                    html += "            <th>密码</th>";
                    html += "            <td><input type='password' name='passWord' value='"+user.getPassWord()+"' onblur='checkPassWord()' id='passWord'/><span id='passWordCheck'></span></td>";
                    html += "        </tr>";
                    html += "               <tr>";
                    html += "                   <td colspan='2' align='center'>";
                    html += "                   <input type='submit' value='保存'/>&nbsp;";
                    html += "                   <input type='reset' value='重置'/></td>";
                    html += "                </tr>";
                    html += "    </table>";
                    html += "</form>";
                    html += "<center><a href='" + req.getContextPath() + "/index'>返回主页</a>&nbsp;&nbsp;&nbsp;<a href='" + req.getContextPath() + "/logOut'>安全退出</a></center>";
                    html += "</body>";
                    html += "<script type='text/javascript' src='js/checkUser.js' ></script>";
                    html += "</html>";

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
