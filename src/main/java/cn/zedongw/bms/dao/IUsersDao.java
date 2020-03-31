package cn.zedongw.bms.dao;

import cn.zedongw.bms.entity.Users;
import org.dom4j.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author ZeDongW
 * @Description Users数据访问层接口
 * @Version 1.0
 * @date ：Created in 2019/4/20 0020 7:21
 * @modified By：
 */
public interface IUsersDao {
    /**
     * 功能描述: 查看所有用户
     *
     * @param users 1
     * @throws
     * @methodName: showUsers
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 8:44
     */
    void showUsers(ArrayList<Users> users);

    /**
     * 功能描述: 管理员删除用户
     * @methodName: deleteUser
     * @param users 1
     * @param domUsers 2
     * @param sc 3
     * @throws IllegalAccessException
     * @throws IOException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 8:45
     */
    void deleteUser(ArrayList<Users> users, Document domUsers, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 功能描述: 管理员修改用户
     * @methodName: updateUser
     * @param users 1
     * @param domUsers 2
     * @param sc 3
     * @throws IllegalAccessException
     * @throws IOException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 8:45
     */
    void updateUser(ArrayList<Users> users, Document domUsers, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 功能描述: 普通用户修改密码
     * @methodName: updatePassword
     * @param users 1
     * @param user 2
     * @param domUsers 3
     * @param sc 4
     * @throws IllegalAccessException
     * @throws IOException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 8:46
     */
    void updatePassword(ArrayList<Users> users, Users user, Document domUsers, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 功能描述: 普通用户修改用户名
     * @methodName: updateUserName
     * @param users 1
     * @param user 2
     * @param domUsers 3
     * @param sc 4
     * @throws IllegalAccessException
     * @throws IOException
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/18 0018 8:46
     */
    void updateUserName(ArrayList<Users> users, Users user, Document domUsers, Scanner sc) throws IllegalAccessException, IOException;
}
