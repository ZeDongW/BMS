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
    private String userName; //用户名
    private String passWord; //密码

    public Users() {
    }

    public Users(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String password) {
        this.passWord = password;
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
                ", password='" + passWord + '\'' +
                '}';
    }
}
