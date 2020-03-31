package cn.zedongw.bms.listener;

import cn.zedongw.bms.entity.Users;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Set;

/**
 * @Author ZeDongW
 * @ClassName OffLineListener
 * @Description 用于监听用户下线
 * @Version 1.0
 * @date ：Created in 2019/7/12 8:27
 * @modified By：
 */

public class OffLineListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    /**
     * @Author: ZeDongW
     * @Description: Session销毁时，从在线用户列表中移除当前用户
     * @Date: 2019/7/14 21:13
     * @Param: [httpSessionEvent]
     * @return: void
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        //获取Session对象
        HttpSession session = httpSessionEvent.getSession();

        //从Session中获取当前登录用户
        Users loginUser = (Users) session.getAttribute("loginUser");

        //获取Servlet Context对象
        ServletContext servletContext = session.getServletContext();

        //从ServletContext对象中获取在线用户列表
        Set<Users> onLineSet = (Set<Users>) servletContext.getAttribute("onLineSet");

        //在线列表不为空，从在线列表中移除该用户
        if (onLineSet != null) {
            onLineSet.remove(loginUser);
        }
    }
}
