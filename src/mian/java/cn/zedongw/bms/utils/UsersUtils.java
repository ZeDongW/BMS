package cn.zedongw.bms.utils;

import cn.zedongw.bms.entity.Users;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author ZeDongW
 * @ClassName UsersUtils
 * @Description 用户工具
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:55
 * @modified By：
 */

public class UsersUtils {
    /**
     * @Author: ZeDongW
     * @Description: 将结果封装为对象
     * @Date: 2019/6/2 0002 20:38
     * @Param: [book, rs]
     * @return: void
     */
    public static void getUser(Users user, ResultSet rs) throws SQLException {
        user.setId(rs.getString("id"));
        user.setUserName(rs.getString("userName"));
        user.setPassWord(rs.getString("passWord"));
    }

    /**
     * @Author: ZeDongW
     * @Description: 将请求封装为对象
     * @Date: 2019/6/2 0002 20:51
     * @Param: [req, id]
     * @return: cn.zedongw.entity.Books
     */
    public static Users setUser(HttpServletRequest req, String id) {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        return new Users(id,userName,passWord);
    }
}
