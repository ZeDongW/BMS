package cn.zedongw.bms.servlet;

import cn.zedongw.bms.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName BaseServlet
 * @Description: servlet父类
 * @Author ZeDongW
 * @Date 2020/3/29 0029 9:39
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取session对象
        HttpSession session = req.getSession(false);

        //session不为空
        if(session != null){

            //获取登录用户
            Users loginUser = (Users)session.getAttribute("loginUser");

            //登录用户不为空
            if(loginUser != null){
                //业务逻辑处理
                doProcess(req, resp);
            } else {
                //等路用户为空，转发到登录界面
                req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(req, resp);
            }
        } else {
            //session为空，转发到登录界面
            req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(req, resp);
        }
    }

    /**
     * Description: servlet业务逻辑处理
     * @methodName: doProcess
     * @param req 1
     * @param resp 2
     * @throws ServletException
     * @throws IOException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/29 0029 9:51
     */
    public abstract void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
