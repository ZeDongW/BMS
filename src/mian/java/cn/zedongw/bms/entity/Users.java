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
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    public Users() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(userName, users.userName) &&
                Objects.equals(passWord, users.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, passWord);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Users(String id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
