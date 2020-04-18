package cn.zedongw.bms.filter;

import cn.zedongw.bms.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author ZeDongW
 * @ClassName LoginFilter
 * @Description 过滤未登陆的数据
 * @Version 1.0
 * @date ：Created in 2019/7/12 7:29
 * @modified By：
 */

public class LoginFilter implements Filter {

    private final static String PATH_SEPARATOR = "/";
    /**
     * 放行列表
     */
    private final Set<String> ignoreSet = new HashSet<>();
    private final Set<String> repeatLoginSet = new HashSet<>();


    Logger logger = LogManager.getLogger(LoginFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) {
        //初始化放行列表
        String ignoresParam = filterConfig.getInitParameter("ignores");
        String[] ignoreArray = ignoresParam.split(",");
        Collections.addAll(ignoreSet, ignoreArray);
        String repeatLoginParam = filterConfig.getInitParameter("repeatLogin");
        String[] repeatLoginArray = repeatLoginParam.split(",");
        Collections.addAll(repeatLoginSet, repeatLoginArray);
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

        logger.info("=================================================请求URI:{}===================================================", requestUri);

        //判断是否登录
        //获取Session对象
        HttpSession session = req.getSession(false);
        //会话不为空
        if (session != null) {
            //从会话中获取用户
            Users loginUser = (Users) session.getAttribute("loginUser");
            //用户不为空
            if (loginUser != null) {
                //判断是否重复登录
                if (isCondition(requestUri, repeatLoginSet) || PATH_SEPARATOR.equals(requestUri)) {
                    resp.sendRedirect(req.getContextPath() + "/users_index");
                    return;
                }
                //放行
                filterChain.doFilter(req, resp);
                return;
            }
        }

        if (isCondition(requestUri, ignoreSet)) {
            //直接放行
            filterChain.doFilter(req, resp);
            return;
        } else {
            //未登录，转发到登陆页面
            resp.sendRedirect(req.getContextPath() + "/users_toLogin");
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * Description: 判断是否满足条件
     *
     * @param requestUri 1
     * @param set        2
     * @throws
     * @methodName: isCondition
     * @return: boolean
     * @author: ZeDongW
     * @date: 2020/4/18 0018 22:11
     */
    private boolean isCondition(String requestUri, Set<String> set) {
        for (String s : set) {
            if (requestUri.endsWith(s)) {
                return true;
            }
        }
        return false;
    }
}
