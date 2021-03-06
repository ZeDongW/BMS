package cn.zedongw.bms.service;

import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;

/**
 * @InterfaceName IUsersService
 * @Description: 用户业务逻辑层接口
 * @Author ZeDongW
 * @Date 2020/3/28 0028 19:44
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public interface IUsersService {

    /**
     * 功能描述: 添加用户
     * @methodName: addUsers
     * @param user 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:45
     */
    void addUsers(Users user);

    /**
     * 功能描述: 根据ID删除用户
     * @methodName: deleteUsers
     * @param id 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:45
     */
    void deleteUsers(String id);

    /**
     * 功能描述: 修改用户
     * @methodName: updateUsers
     * @param user 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:45
     */
    void updateUsers(Users user);

    /**
     * 功能描述: 通过ID查找用户
     * @methodName: findUsersById
     * @param id 1
     * @throws
     * @return: cn.zedongw.bms.entity.Users
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:45
     */
    Users findUsersById(String id);

    /**
     * Description: 分页查找所有用户
     *
     * @param pb 1
     * @throws
     * @methodName: findAllUsers
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/30 0030 14:02
     */
    void findAllUsers(PageBean<Users> pb);

    /**
     * Description: 根据用户名密码查找用户
     *
     * @param userName 1
     * @param passWord 2
     * @throws
     * @methodName: findByUnAndPwd
     * @return: cn.zedongw.bms.entity.Users
     * @author: ZeDongW
     * @date: 2020/3/29 0029 13:56
     */
    Users findByUnAndPwd(String userName, String passWord);

    /**
     * Description: 查看用户名是否存在
     * @methodName: userNameExists
     * @param userName 1
     * @throws
     * @return: java.lang.Boolean
     * @author: ZeDongW
     * @date: 2020/3/29 0029 13:56
     */
    Boolean userNameExists(String userName);
}
