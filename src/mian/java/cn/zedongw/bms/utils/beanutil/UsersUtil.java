package cn.zedongw.bms.utils.beanutil;

import cn.zedongw.bms.entity.Users;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;

/**
 * @Author ZeDongW
 * @ClassName UsersUtil
 * @Description 用户数据封装
 * @Version 1.0
 * @date ：Created in 6/28/2019 8:19 AM
 * @modified By：
 */

public class UsersUtil {

    /**
     * @Author: ZeDongW
     * @Description: 从数据库查询结果集中封装对象
     * @Date: 6/30/2019 11:50 AM
     * @Param: [rs]
     * @return: cn.zedongw.entity.Users
     */
    public static Users getBean(ResultSet rs){
        Users user = new Users();

        try {
            String id = rs.getString("id");
            String userName = rs.getString("userName");
            String passWord = rs.getString("passWord");
            String role = rs.getString("role");

            BeanUtils.copyProperty(user, "id", id);
            BeanUtils.copyProperty(user, "userName", userName);
            BeanUtils.copyProperty(user, "passWord", passWord);
            BeanUtils.copyProperty(user, "role", role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    /**
     * @Author: ZeDongW
     * @Description: 从HttpServletRequest中封装对象
     * @Date: 6/30/2019 11:50 AM
     * @Param: [req]
     * @return: cn.zedongw.entity.Users
     */
    public static Users getBean(HttpServletRequest req, String id){
        Users user = new Users();
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String role = req.getParameter("role");

        try {
            if (id != null){
                BeanUtils.copyProperty(user, "id", id);
            }
            BeanUtils.copyProperty(user, "userName", userName);
            BeanUtils.copyProperty(user, "passWord", passWord);
            if (role == null){
                BeanUtils.copyProperty(user, "role", "0");
            } else {
                BeanUtils.copyProperty(user, "role", role);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
