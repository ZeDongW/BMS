package cn.zedongw.bms.action;

import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import cn.zedongw.bms.service.impl.UsersServiceImpl;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Set;

/**
 * @ClassName UsersAction
 * @Description: 用户Action类
 * @Author ZeDongW
 * @Date 2020/3/1 0001 18:24
 * @Version V1.0
 **/
public class UsersAction extends ActionSupport implements ModelDriven<Users> {

    Logger logger = LogManager.getLogger(UsersAction.class.getName());

    private IUsersService service = new UsersServiceImpl();

    private Users user = new Users();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    private ActionContext ac = ActionContext.getContext();

    private HttpServletRequest req = (HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);

    private HttpSession session = req.getSession(false);

    private ServletContext servletContext= ServletActionContext.getServletContext();


    /**
     *@Description struts2模型驱动
     *@Author ZeDongW
     *@Date 2020/3/1 0001 19:50
     *@Param []
     *@Return cn.zedongw.entity.Users
     */
    @Override
    public Users getModel() {
        return user;
    }

    /**
     *@Description 用户登录
     *@Author ZeDongW
     *@Date 2020/3/1 0001 18:25
     *@Param []
     *@Return java.lang.String
     */
    public String login(){
        logger.info("=======================用户登录,用户名：{}======================", user.getUserName());

        session.setAttribute("loginUser",user);
        Set<Users> onLineSet =  (Set<Users>) servletContext.getAttribute("onLineSet");
        if (onLineSet != null) {
            onLineSet.add(user);
        }
        return "index";
    }

    public void validateLogin(){
        if (service.userNameExists(user.getUserName())){
            user = service.findByUnAndPwd(user.getUserName(), user.getPassWord());

            if (user == null) {
                super.addFieldError("passWord","密码错误，请输入正确密码");
            }
        } else {
            super.addFieldError("userName","用户名不存在");
        }
    }

    /**
     *@Description 用户注册
     *@Author ZeDongW
     *@Date 2020/3/8 0008 18:41
     *@Param []
     *@Return java.lang.String
     */
    public String regist(){

        //获取参数
        String userName = user.getUserName();

        logger.info("=========================用户注册，用户名：{}========================", userName);

        //返回消息
        String message;

        //用户名存在
        if(service.userNameExists(userName)){
            message = "5";
            req.setAttribute("message", message);
        } else { //用户名不存在
            if (user.getRole() == null) {
                user.setRole("0");
            }
            if (user.getId() == null) {
                user.setId(IDUtils.getUuid());
            }
            //调用业务逻辑层插入数据
            service.addUsers(user);
            String success = "1";
            req.setAttribute("success", success);
        }
        return "login";
    }

    /**
     *@Description 用户注销
     *@Author ZeDongW
     *@Date 2020/3/8 0008 18:57
     *@Param []
     *@Return java.lang.String
     */
    public String logOut() {
        logger.info("===============================用户注销===============================");

        //Session不为空
        if(session != null){
            //销毁Session
            session.invalidate();
        }

        return "login";
    }

    /**
     *@Description 删除用户
     *@Author ZeDongW
     *@Date 2020/3/8 0008 19:10
     *@Param []
     *@Return java.lang.String
     */
    public String delete(){
        logger.info("=====================删除用户，用户ID：{}=======================", user.getId());
        //删除用户
        service.deleteUsers(user.getId());

        return list();
    }

    /**
     *@Description
     *@Author ZeDongW
     *@Date 2020/3/8 0008 19:18
     *@Param []
     *@Return java.lang.String
     */
    public String query(){
        logger.info("==================根据id查找用户，用户ID：{}=====================", user.getId());
        //根据id查找用户
        Users userInfo = service.findUsersById(user.getId());
        if (userInfo != null) {
            BeanUtils.copyProperties(userInfo, user);
        }
        return "query";
    }

    /**
     *@Description 更新用户
     *@Author ZeDongW
     *@Date 2020/3/8 0008 19:46
     *@Param []
     *@Return java.lang.String
     */
    public String update() {
        logger.info("================更新用户，用id:{}===============", user.getId());
        //更新用户
        service.updateUsers(user);
        return list();
    }

    /**
     *@Description 分页查询用户
     *@Author ZeDongW
     *@Date 2020/3/8 0008 19:52
     *@Param []
     *@Return java.lang.String
     */
    public String list(){
        logger.info("================获取所有用户===============");

        //实例化分页查询对象
        PageBean<Users> usersPb = new PageBean<>();

        //从request中获取分页实体
        PageBean<Users> usersPb2 = (PageBean<Users>) req.getSession().getAttribute("usersPb");

        //封装分页实体
        PageBeanUtils.initPageBean(req, usersPb, usersPb2);

        //获取所有用户
        service.findAllUsers(usersPb);

        //获取排序信息
        String sort = req.getParameter("sort");

        //获取用户集合
        ArrayList<Users> usersList = usersPb.getPageData();

        //给用户排序
        Sort.sort(usersList, sort);

        //将排序后的对象放入分页查询对象中
        usersPb.setPageData(usersList);

        //将用户封装到request中
        req.setAttribute("usersPb", usersPb);
        return "usersList";
    }

    /**
     * Description: 进入菜单列表
     * @methodName: index
     * @param
     * @throws
     * @return: java.lang.String
     * @author: ZeDongW
     * @date: 2020/3/31 0031 18:06
     */
    public String index(){
        logger.info("================进入主页===============");
        return "index";
    }

    /**
     * Description: 获取在线用户列表
     * @methodName: onLineSet
     * @param
     * @throws
     * @return: java.lang.String
     * @author: ZeDongW
     * @date: 2020/3/31 0031 21:50
     */
    public String onLineSet(){
        logger.info("================在线用户列表===============");
        return "onLineSet";
    }

    /**
     * Description: 眺往登陆页面
     * @methodName: toLogin
     * @param
     * @throws
     * @return: java.lang.String
     * @author: ZeDongW
     * @date: 2020/4/1 0001 9:34
     */
    public String toLogin(){
        logger.info("================未登录，眺往登录页面===============");
        return "login";
    }
}
