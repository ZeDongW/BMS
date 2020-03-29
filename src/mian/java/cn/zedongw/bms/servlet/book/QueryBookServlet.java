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
                    String html = getHtml(req, id, user, book);

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

    private String getHtml(HttpServletRequest req, String id, Users user, Books book) {
        String html = "";
        html += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
        html += "<html xmlns='http://www.w3.org/1999/xhtml'>";
        html += "<head>";
        html += "    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
        if (id != null) {
            html += "    <title>修改书本</title>";
        } else {
            html += "    <title>添加书本</title>";
        }
        html += "</head>";
        html += "<body>";
        html += "<div style='text-align: center'>欢迎登陆， <font size='+2' color='green'> " + user.getUserName() + " </font><br />";
        if (id != null) {
            html += "    <h3>修改书本</h3></div>";
        } else {
            html += "    <h3>添加书本</h3></div>";
        }
        html += "<form action=' " + req.getContextPath();
        if (id != null) {
            html += "/updateBook'";
        } else {
            html += "/addBook'";
        }
        html += " method='post' onsubmit='return checkForm()'>";
        if (id != null) {
            html += "    <input type='hidden' name='id' value='" + book.getId() + "'/>";
        }
        html += "    <table align='center' border='1' width='300px'>";
        html += "        <tr>";
        html += "            <th>书名</th>";
        if (id != null) {
            html += "            <td><input type='text' name='bookName' value='" + book.getBookName() + "'/></td>";
        } else {
            html += "            <td><input type='text' name='bookName'/></td>";
        }
        html += "        </tr>";
        html += "        <tr>";
        html += "            <th>作者</th>";
        if (id != null) {
            html += "            <td><input type='text' name='bookAuthor' value='" + book.getBookAuthor() + "'/></td>";
        } else {
            html += "            <td><input type='text' name='bookAuthor'/></td>";
        }
        html += "        </tr>";
        html += "        <tr>";
        html += "            <th>出版社</th>";
        if (id != null) {
            html += "            <td><input type='text' name='publisher' value='" + book.getPublisher() + "'/></td>";
        } else {
            html += "            <td><input type='text' name='publisher'/></td>";
        }

        html += "        </tr>";
        html += "        <tr>";
        html += "            <th>价格</th>";
        if (id != null) {
            html += "            <td><input type='text' name='price' value='" + book.getPrice() + "' onblur='checkPrice()' id='price'/><span id='priceCheck'></span></td>";
        } else {
            html += "            <td><input type='text' name='price' onblur='checkPrice()' id='price'/><span id='priceCheck'></span></td>";
        }

        html += "        </tr>";
        html += "        <tr>";
        html += "            <th>书号</th>";
        if (id != null) {
            html += "            <td><input type='text' name='bookNum' value='" + book.getBookNum() + "' onblur='checkBookNum()' id='bookNum'/><span id='bookNumCheck'></span></td>";
        } else {
            html += "            <td><input type='text' name='bookNum' onblur='checkBookNum()' id='bookNum'/><span id='bookNumCheck'></span></td>";
        }

        html += "        </tr>";
        html += "        <tr>";
        html += "            <th>出版日期</th>";
        if (id != null) {
            html += "            <td><input type='text' name='publishDate' value='" + book.getPublishDate() + "' onblur='checkPublishDate()' id='publishDate'/><span id='publishDateCheck'></span></td>";
        } else {
            html += "            <td><input type='text' name='publishDate' onblur='checkPublishDate()' id='publishDate'/><span id='publishDateCheck'></span></td>";
        }

        html += "        </tr>";
        html += "        <tr>";
        html += "            <td colspan='2' align='center'>";
        html += "            <input type='submit' value='保存'/>&nbsp;";
        html += "            <input type='reset' value='重置'/></td>";
        html += "        </tr>";
        html += "    </table>";
        html += "</form>";
        html += "<div style='text-align: center'><a href='" + req.getContextPath() + "/index'>返回主页</a>&nbsp;&nbsp;&nbsp;<a href=' " + req.getContextPath() + "/queryUser?id=" + user.getId() + "'>用户修改</a>&nbsp;&nbsp;&nbsp;<a href='" + req.getContextPath() + "/logOut'>安全退出</a></div>";
        html += "</body>";
        html += "<script type='text/javascript' src='js/checkBook.js' ></script>";
        html += "</html>";
        return html;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
