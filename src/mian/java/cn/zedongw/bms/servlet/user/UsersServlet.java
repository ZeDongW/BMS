package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;
import cn.zedongw.bms.utils.PageBeanUtils;
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

        //实例化分页查询对象
        PageBean<Users> usersPb = new PageBean<>();

        //从request中获取分页实体
        PageBean<Users> usersPb2 = (PageBean<Users>) req.getSession().getAttribute("usersPb");

        //封装分页实体
        PageBeanUtils.initPageBean(req, usersPb, usersPb2);

        //获取所有用户
        service.findAllUsers(usersPb);

        //获取排序信息
        String sort = req.getParameter("sort");

        //获取用户集合
        ArrayList<Users> usersList = usersPb.getPageData();

        //给用户排序
        Sort.sort(usersList, sort);

        //将排序后的对象放入分页查询对象中
        usersPb.setPageData(usersList);

        //将集合放入请求中
        req.getSession().setAttribute("usersPb", usersPb);

        //重定向到用户列表页面
        req.getRequestDispatcher("/WEB-INF/page/users.jsp").forward(req, resp);
    }

}
