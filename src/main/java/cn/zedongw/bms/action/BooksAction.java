package cn.zedongw.bms.action;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.service.IBooksService;
import cn.zedongw.bms.service.impl.BooksServiceImpl;
import cn.zedongw.bms.utils.IDUtils;
import cn.zedongw.bms.utils.PageBeanUtils;
import cn.zedongw.bms.utils.comparator.Sort;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @ClassName BooksAction
 * @Description: 书本类Action处理
 * @Author ZeDongW
 * @Date 2020/3/8 0008 19:56
 * @Version V1.0
 **/
public class BooksAction extends ActionSupport implements ModelDriven<Books> {

    Logger logger = LogManager.getLogger(BooksAction.class.getName());

    private final IBooksService service = new BooksServiceImpl();

    private Books book = new Books();

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    private final ActionContext ac = ActionContext.getContext();

    private final HttpServletRequest req = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);

    @Override
    public Books getModel() {
        return book;
    }

    /**
     *@Description 添加书本
     *@Author ZeDongW
     *@Date 2020/3/8 0008 20:04
     *@Param []
     *@Return java.lang.String
     */
    public String add(){
        logger.info("========添加书本成功，书本名：{}=========", book.getBookName());

        //书本ID为空
        if (book.getId() == null) {
            //设置书本ID
            book.setId(IDUtils.getUuid());
        }

        //添加书本
        service.addBooks(book);
        return list();
    }

    /**
     *@Description 分页查询书本
     *@Author ZeDongW
     *@Date 2020/3/8 0008 20:10
     *@Param []
     *@Return java.lang.String
     */
    public String list(){
        logger.info("========获取所有书本=========");

        //实例化分页查询对象
        PageBean booksPb = new PageBean<>();

        //从Request中获取分页实体
        PageBean<Books> booksPb2 = (PageBean<Books>) req.getSession().getAttribute("booksPb");

        //封装分页实体
        PageBeanUtils.initPageBean(req, booksPb, booksPb2);

        //获取所有书本
        service.findAllBooks(booksPb);

        //获取排序参数
        String sort = req.getParameter("sort");

        //获取书本集合
        ArrayList<Books> booksList = booksPb.getPageData();

        //给书本排序
        Sort.sort(booksList, sort);

        //将排序后的对象放入分页查询对象中
        booksPb.setPageData(booksList);

        //将集合放入请求中
        req.getSession().setAttribute("booksPb", booksPb);
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
        service.deleteBooks(book.getId());
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
        Books bookInfo = service.findBooksById(book.getId());
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
        service.updateBooks(book);
        return "list";
    }
}
