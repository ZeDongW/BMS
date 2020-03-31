package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;
import cn.zedongw.bms.utils.IDUtils;
import cn.zedongw.bms.utils.beanutil.UsersUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        //注册信息
        String message;

        //成功信息
        String success = "1";

        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //实例化用户业务逻辑层
        IUsersService service = new UsersServiceImpl();

        //封装User对象
        Users user = UsersUtil.getBean(req, IDUtils.getUuid());

        //查看用户名是否存在
        if (service.userNameExists(user.getUserName())) {
            //"该用户名已被注册
            message = "5";
            //将注册返回信息封装到request中
            req.setAttribute("message", message);
        } else {
            if (user.getUserName() == null) {
                //用户名为空
                message = "2";
                req.setAttribute("message", message);
                //转发到登录页面
                req.getRequestDispatcher("WEB-INF/page/login.jsp").forward(req, resp);
                return;
            }
            if (user.getPassWord() == null) {
                //密码为空
                message = "3";
                req.setAttribute("message", message);
                //转发到登录页面
                req.getRequestDispatcher("WEB-INF/page/login.jsp").forward(req, resp);
                return;
            }
            //添加用户
            service.addUsers(user);

            //将注册返回信息封装到request中
            req.setAttribute("success", success);
        }
        //转发到登录页面
        req.getRequestDispatcher("WEB-INF/page/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
