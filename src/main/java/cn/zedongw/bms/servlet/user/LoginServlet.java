package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        //返回消息
        String message = null;

        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取用户属性
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");

        //实例化用户业务逻辑层
        IUsersService service = new UsersServiceImpl();

        //用户名存在
        if(service.userNameExists(userName)){

            //根据用户名密码查找用户
            Users user = service.findByUnAndPwd(userName, passWord);

            //用户不为空，登陆成功
            if (user != null){
                //获取Session对象
                HttpSession session = req.getSession();

                //将登录用户对象放入Session中
                session.setAttribute("loginUser",user);

                //重定向到功能菜单
                resp.sendRedirect(req.getContextPath() + "/index");
                return;
            } else { //登录失败有， 密码错误
                message = "密码错误，请输入正确密码";
            }
        } else { //用户名不存在
            message = "用户名不存在";
        }

        //将返回消息封装到request中
        req.setAttribute("message", message);

        //登录失败，转发失败信息到登录页面
        req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
