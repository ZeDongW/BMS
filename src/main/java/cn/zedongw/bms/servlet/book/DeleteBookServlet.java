package cn.zedongw.bms.servlet.book;

import cn.zedongw.bms.service.IBooksService;
import cn.zedongw.bms.service.impl.BooksServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName DeleteBookServlet
 * @Description 删除书本
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 20:46
 * @modified By：
 */

public class DeleteBookServlet extends BaseServlet {
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

        //实例化书本业务逻辑层
        IBooksService service = new BooksServiceImpl();

        //获取书本ID
        String id = req.getParameter("id");

        //书本ID不为空
        if(id != null){
            //根据ID删除书本
            service.deleteBooks(id);
        }

        //重定向到书本列表
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
