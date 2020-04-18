package cn.zedongw.bms.action;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.service.IBooksService;
import cn.zedongw.bms.utils.PageBeanUtils;
import cn.zedongw.bms.utils.comparator.Sort;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @ClassName BooksAction
 * @Description: 书本类Action处理
 * @Author ZeDongW
 * @Date 2020/3/8 0008 19:56
 * @Version V1.0
 **/
@Controller
@Scope("prototype")
public class BooksAction extends ActionSupport implements ModelDriven<Books> {

    Logger logger = LogManager.getLogger(BooksAction.class.getName());

    private IBooksService booksService;

    @Autowired
    public void setBooksService(IBooksService booksService) {
        this.booksService = booksService;
    }

    private Books book = new Books();

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    @Override
    public Books getModel() {
        return book;
    }

    private final ActionContext ac = ActionContext.getContext();

    private final HttpServletRequest req = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);

    private final HttpSession session = req.getSession(false);

    /**
     * @Description 添加书本
     * @Author ZeDongW
     * @Date 2020/3/8 0008 20:04
     * @Param []
     * @Return java.lang.String
     */
    public String add() {
        logger.info("========添加书本成功，书本名：{}=========", book.getBookName());
        //添加书本
        booksService.addBooks(book);
        return list();
    }

    /**
     *@Description 分页查询书本
     *@Author ZeDongW
     *@Date 2020/3/8 0008 20:10
     *@Param []
     *@Return java.lang.String
     */
    public String list() {
        logger.info("========获取所有书本=========");

        //实例化分页查询对象
        PageBean booksPb = new PageBean<>();

        //从Request中获取分页实体
        PageBean<Books> booksPb2 = (PageBean<Books>) session.getAttribute("booksPb");

        //封装分页实体
        PageBeanUtils.initPageBean(req, booksPb, booksPb2);

        //获取所有书本
        booksService.findAllBooks(booksPb);

        //获取排序参数
        String sort = req.getParameter("sort");

        //获取书本集合
        ArrayList<Books> booksList = booksPb.getPageData();

        //给书本排序
        Sort.sort(booksList, sort);

        //将排序后的对象放入分页查询对象中
        booksPb.setPageData(booksList);

        //将集合放入请求中
        session.setAttribute("booksPb", booksPb);
        return "booksList";
    }

    /**
     *@Description 根据id查找书本
     *@Author ZeDongW
     *@Date 2020/3/8 0008 20:14
     *@Param []
     *@Return java.lang.String
     */
    public String delete(){
        logger.info("=========根据ID删除书本，书本ID：{}=============", book.getId());
        //删除书本
        booksService.deleteBooks(book.getId());
        return list();

    }

    /**
     *@Description 根据id查找书本
     *@Author ZeDongW
     *@Date 2020/3/8 0008 20:30
     *@Param []
     *@Return java.lang.String
     */
    public String query(){
        logger.info("===================根据ID查找书本，书本ID:{}===========", book.getId());

        //获取书本id
        Books bookInfo = booksService.findBooksById(book.getId());
        if (bookInfo != null) {
            BeanUtils.copyProperties(bookInfo, book);
        }
        return "query";
    }

    /**
     *@Description 更新书本
     *@Author ZeDongW
     *@Date 2020/3/8 0008 20:33
     *@Param []
     *@Return java.lang.String
     */
    public String update(){
        logger.info("=========根据ID更新书本， 书ID：{}===========", book.getId());
        //更新书本
        booksService.updateBooks(book);
        return "list";
    }
}
