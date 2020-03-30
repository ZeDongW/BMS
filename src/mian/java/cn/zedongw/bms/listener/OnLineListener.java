package cn.zedongw.bms.listener;

import cn.zedongw.bms.entity.Users;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author ZeDongW
 * @ClassName OnLineListener
 * @Description 初始化在线列表
 * @Version 1.0
 * @date ：Created in 2019/7/12 8:26
 * @modified By：
 */

public class OnLineListener implements ServletContextListener {
    /**
     * @Author: ZeDongW
     * @Description: 创建ServletContext对象时，初始化在线用户列表
     * @Date: 2019/7/12 8:30
     * @Param: [servletContextEvent]
     * @return: void
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化在线用户列表
        Set<Users> onLineSet = new HashSet<>();

        //将在线列表放入ServletContext对象中
        servletContextEvent.getServletContext().setAttribute("onLineSet", onLineSet);
    }

    /**
     * @Author: ZeDongW
     * @Description: 销毁ServletContext对象时，移除在线用户列表
     * @Date: 2019/7/14 21:08
     * @Param: [servletContextEvent]
     * @return: void
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //获取ServletContext对象
        ServletContext servletContext = servletContextEvent.getServletContext();

        //从ServletContext对象中获取在线用户列表
        Object onLineSet = servletContext.getAttribute("onLineSet");

        //用户列表不为空，则移除
        if (onLineSet != null) {
            servletContext.removeAttribute("onLineSer");
        }
    }
}
