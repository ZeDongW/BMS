package cn.zedongw.bms.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName LogOutServlet
 * @Description 用户注销
 * @Version 1.0
 * @date ：Created in 2019/5/6 0006 6:53
 * @modified By：
 */

public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        if(session != null){
            String id = (String)session.getAttribute("id");
            if(id != null){
                session.removeAttribute("id");
                resp.getWriter().write("安全退出成功，3秒后返回登录页面");
                resp.setHeader("refresh","3;url= " + req.getContextPath() + "/index.html”");
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
