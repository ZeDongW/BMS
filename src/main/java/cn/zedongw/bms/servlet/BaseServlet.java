package cn.zedongw.bms.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName BaseServlet
 * @Description: servlet父类
 * @Author ZeDongW
 * @Date 2020/3/29 0029 9:39
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //核心业务处理
        doProcess(req, resp);
    }

    /**
     * Description: servlet业务逻辑处理
     * @methodName: doProcess
     * @param req 1
     * @param resp 2
     * @throws ServletException
     * @throws IOException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/29 0029 9:51
     */
    public abstract void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
