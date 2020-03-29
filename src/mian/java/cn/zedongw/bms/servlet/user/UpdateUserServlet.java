package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;
import cn.zedongw.bms.utils.beanutil.UsersUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName UpdateUserServlet
 * @Description 修改用户Servlet
 * @Version 1.0
 * @date ：Created in 2019/5/6 0006 7:45
 * @modified By：
 */

public class UpdateUserServlet extends BaseServlet {
    /**
     * Description: servlet业务逻辑处理
     *
     * @param req  1
     * @param resp 2
     * @throws ServletException
     * @throws IOException
     * @methodName: doProcess
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/29 0029 9:51
     */
    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //实例化用户业务逻辑层
        IUsersService service = new UsersServiceImpl();

        //获取用户ID
        String id = req.getParameter("id");

        //封装用户对象
        Users user = UsersUtil.getBean(req, id);

        //更新用户
        service.updateUsers(user);

        //重定向到用户页面
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
