package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;
import cn.zedongw.bms.utils.comparator.Sort;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName UsersServlet
 * @Description 用户管理Servlet
 * @Version 1.0
 * @date ：Created in 2019/5/6 0006 7:05
 * @modified By：
 */

public class UsersServlet extends BaseServlet {
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

        //查询所有用户
        ArrayList<Users> usersList = service.findAllUsers();

        //获取排序属性
        String sort = req.getParameter("sort");

        //根据排序属性排序
        usersList = Sort.sort(usersList,sort);

        //将书本列表封装到request中
        req.setAttribute("usersList", usersList);

        //重定向到用户列表页面
        req.getRequestDispatcher("/WEB-INF/page/users.jsp").forward(req, resp);
    }
}
