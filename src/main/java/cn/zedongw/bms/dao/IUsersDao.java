package cn.zedongw.bms.dao;

import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;

/**
 * @InterfaceName IUsersDao
 * @Description: 用户持久层接口
 * @Author ZeDongW
 * @Date 2020/3/28 0028 19:53
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public interface IUsersDao {

    /**
     * Description: 新增用户
     *
     * @param user 1
     * @throws
     * @methodName: add
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:54
     */
    void add(Users user);

    /**
     * Description: 根据ID删除用户
     *
     * @param id 1
     * @throws
     * @methodName: delete
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:55
     */
    void delete(String id);

    /**
     * Description: 更新用户
     *
     * @param user 1
     * @throws
     * @methodName: update
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/28 0028 19:55
     */
    void update(Users user);

    /**
     * Description: 分页查询所有用户
     *
     * @param pb 1
     * @throws
     * @methodName: findAll
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/30 0030 13:50
     */
    void findAll(PageBean<Users> pb);

    /**
     * escription: 根据ID查找用户
     *
     * @param id 1
     * @throws
     * @methodName: findById
     * @return: cn.zedongw.bms.entity.Users
     * @author: ZeDongW
     * @date: 2020/3/28 0028 20:00
     */
    Users findById(String id);

    /**
     * Description:  根据用户名密码查找用户
     *
     * @param userName 1
     * @param passWord 2
     * @throws
     * @methodName: findByUnAndPwd
     * @return: cn.zedongw.bms.entity.Users
     * @author: ZeDongW
     * @date: 2020/3/28 0028 20:00
     */
    Users findByUnAndPwd(String userName, String passWord);

    /**
     * Description: 判断用户名是否存在
     *
     * @param userName 1
     * @throws
     * @methodName: userNameExists
     * @return: java.lang.Boolean
     * @author: ZeDongW
     * @date: 2020/3/28 0028 20:02
     */
    Boolean userNameExists(String userName);

    /**
     * Description: 获取总记录数
     *
     * @param
     * @throws
     * @methodName: getTotalCount
     * @return: int
     * @author: ZeDongW
     * @date: 2020/3/30 0030 13:20
     */
    int getTotalCount();
}
