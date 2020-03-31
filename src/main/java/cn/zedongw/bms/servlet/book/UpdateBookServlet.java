package cn.zedongw.bms.servlet.book;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.service.IBooksService;
import cn.zedongw.bms.service.impl.BooksServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;
import cn.zedongw.bms.utils.BooksUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName UpdateBookServlet
 * @Description 修改书本信息
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 21:35
 * @modified By：
 */

public class UpdateBookServlet extends BaseServlet {
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

        //获取书本id
        String id = req.getParameter("id");

        //将书本信息封装成对象
        Books book = BooksUtils.setBook(req, id);

        //跟新书本
        service.updateBooks(book);

        //重定向到书本列表
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
