package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName QueryUserServlet
 * @Description 查询用户
 * @Version 1.0
 * @date ：Created in 2019/5/11 0011 16:23
 * @modified By：
 */

public class QueryUserServlet extends BaseServlet {
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

        //根据ID查找用户
        Users user = service.findUsersById(id);

        //将用户封装都请求中
        req.setAttribute("user", user);

        //转发到修改用户界面
        req.getRequestDispatcher("/WEB-INF/page/queryUser.jsp").forward(req, resp);
    }
}