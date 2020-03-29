package cn.zedongw.bms.servlet.user;

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

        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取session对象
        HttpSession session = req.getSession(false);

        //session不为空
        if(session != null){

            //销毁Session
            session.invalidate();
        }

        //转发到登陆页面
        req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
