package cn.zedongw.bms.filter;

import cn.zedongw.bms.entity.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName LoginFilter
 * @Description 过滤未登陆的数据
 * @Version 1.0
 * @date ：Created in 2019/7/12 7:29
 * @modified By：
 */

public class LoginFilter implements Filter {

    /**
     * 放行列表
     */
    private ArrayList<String> releaseList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        //初始化放行列表

        //登录
        releaseList.add("login");
        releaseList.add("login.jsp");
        //注册
        releaseList.add("regist");
        //注销
        releaseList.add("logOut");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //转换对象
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //统一编码格式处理
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取请求资源
        String requestUri = req.getRequestURI();

        //获取请求参数
        String path = requestUri.substring(requestUri.lastIndexOf("/") + 1);


        //登录注册注销，直接放行
        if (releaseList.contains(path)) {
            //直接放行
            filterChain.doFilter(req, resp);
        } else {
            //获取Session对象
            HttpSession session = req.getSession(false);
            //会话不为空
            if (session != null) {
                //从会话中获取用户
                Users loginUser = (Users) session.getAttribute("loginUser");
                //用户不为空
                if (loginUser != null) {
                    //放行
                    filterChain.doFilter(req, resp);
                    return;
                }
            }
            //未登录，转发到登陆页面
            req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
