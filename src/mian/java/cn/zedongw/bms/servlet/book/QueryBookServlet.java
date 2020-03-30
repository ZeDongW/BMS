package cn.zedongw.bms.servlet.book;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.service.IBooksService;
import cn.zedongw.bms.service.impl.BooksServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ZeDongW
 * @ClassName QueryBookServlet
 * @Description 查看书本
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 20:57
 * @modified By：
 */

public class QueryBookServlet extends BaseServlet {
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
        IBooksService sercice = new BooksServiceImpl();

        //获取书本ID
        String id = req.getParameter("id");

        //声明书本对象
        Books book;

        //书本ID不为空
        if(id != null){
            //根据ID查找书本
            book = sercice.findBooksById(id);
        } else {
            //书本ID为空，初始化书本对象
            book = new Books();
        }

        //将书本对象封装到request中
        req.setAttribute("book", book);

        //转发到查询书本页面
        req.getRequestDispatcher("WEB-INF/page/queryBook.jsp").forward(req, resp);
    }
}
