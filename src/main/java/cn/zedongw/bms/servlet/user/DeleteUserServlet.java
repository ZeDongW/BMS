package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName DeleteUserServlet
 * @Description 删除用户
 * @Version 1.0
 * @date ：Created in 2019/5/6 0006 7:34
 * @modified By：
 */

public class DeleteUserServlet extends BaseServlet {
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

        //用户ID不为空
        if(id != null){
            //根据ID删除用户
            service.deleteUsers(id);
        }

        //重定向到用户列表
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
