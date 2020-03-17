package cn.zedongw.bms.entity;

import java.util.Objects;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : Users
 * @description：图书馆里系统用户实体类
 * @modified By：
 * @date ：Created in 2019/01/22/0022 22:37
 */
public class Users {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public Users() {
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Users users = (Users) o;
        return userName.equals(users.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
