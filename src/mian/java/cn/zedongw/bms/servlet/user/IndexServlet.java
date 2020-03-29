package cn.zedongw.bms.servlet.user;

import cn.zedongw.bms.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName IndexServlet
 * @Description 用户功能菜单
 * @Version 1.0
 * @date ：Created in 2019/5/5 0005 7:56
 * @modified By：
 */

public class IndexServlet extends BaseServlet {
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
        //转发到功能菜单页面
        req.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(req, resp);
    }
}
