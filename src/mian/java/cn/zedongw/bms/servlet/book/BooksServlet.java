package cn.zedongw.bms.servlet.book;


import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.service.IBooksService;
import cn.zedongw.bms.service.impl.BooksServiceImpl;
import cn.zedongw.bms.servlet.BaseServlet;
import cn.zedongw.bms.utils.comparator.Sort;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName BooksServlet
 * @Description 图书servlet
 * @Version 1.0
 * @date ：Created in 2019/5/12 0012 20:03
 * @modified By：
 */

public class BooksServlet extends BaseServlet {

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

        //查询所有书本
        ArrayList<Books> booksList = service.findAllBooks();

        //获取排序信息
        String sort = req.getParameter("sort");

        //给书本排序
        booksList = Sort.sort(booksList,sort);

        //将书本集合封装到request
        req.setAttribute("booksList", booksList);

        //转发到书本列表页面
        req.getRequestDispatcher("/WEB-INF/page/books.jsp").forward(req, resp);
    }
}