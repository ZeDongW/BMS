package cn.zedongw.bms.app;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.dao.impl.BooksDaoImpl;
import cn.zedongw.bms.dao.impl.UsersDaoImpl;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.Dom4jUtils;
import cn.zedongw.bms.utils.GlobalContants;
import org.dom4j.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author ZeDongW
 * @ClassName UsersControl
 * @Description Users功能
 * @Version 1.0
 * @date ：Created in 2019/4/20 0020 7:21
 * @modified By：
 */

public class UsersControl {
    IBooksDao bd = new BooksDaoImpl();
    IUsersDao ud = new UsersDaoImpl();

    /**
     * @Author: ZeDongW
     * @Description: 系统功能菜单
     * @Date: 2019/4/20 0020 7:44
     * @Param: [users, books, sc]
     * @return: void
     */
    public void sysMenu(ArrayList<Users> users, ArrayList<Books> books, Document domUsers, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        while (true) {
            System.out.println("*****************************************************");
            System.out.println("*                                                   *");
            System.out.println("*                                                   *");
            System.out.println("*                欢迎使用图书馆里系统               *");
            System.out.println("*                                                   *");
            System.out.println("*                                                   *");
            System.out.println("*****************************************************");
            System.out.println("请选择功能： 用户登录(A)  注册用户(B)  退出系统(Q)");

            //获取用户操作，统一转成大写
            String operate = sc.nextLine().toUpperCase();
            switch (operate) {
                case "A":
                    login(users, books, domUsers, domBooks, sc);
                    break;
                case "B":
                    register(users, domUsers, sc);
                    break;
                case "Q":
                    System.out.println("正在退出……");
                    Dom4jUtils.writeXml(Users.class,domUsers);
                    Dom4jUtils.writeXml(Books.class,domBooks);
                    System.out.println("退出成功，欢迎下次登录");
                    System.exit(0);
                    break;
                default:
                    System.out.println("您选择的功能不存在，请重新输入……");
            }
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 图书管理系统菜单
     * @Date : 2019/01/23/0023 21:24
     * @Param : [users, books, sc, s, s1]
     */
    public void menuNor(ArrayList<Users> users, ArrayList<Books> books, Users user, String s, String s1, Document domUsers, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        while (true) {
            System.out.println("请选择功能： 查看图书(A) 添加图书(B) 修改图书(C) 删除图书(D)");
            System.out.println("修改用户名(E) 修改密码(F) " + s + s1);
            System.out.println("查看所有用户(I) 注销(Q)");
            System.out.println("请输入您选择的功能：");
            //获取用户操作，统一转成大写
            String operate = sc.nextLine().toUpperCase();
            switch (operate) {
                case "A":
                    System.out.println("您选择了查看图书功能");
                    bd.showBooks(books, sc);
                    break;
                case "B":
                    System.out.println("您选择了添加图书功能");
                    bd.addBook(books, domBooks, sc);
                    break;
                case "C":
                    System.out.println("您选择了修改图书功能");
                    bd.updateBook(books, domBooks, sc);
                    break;
                case "D":
                    System.out.println("您选择了删除图书功能");
                    bd.deleteBook(books, domBooks, sc);
                    break;
                case "E":
                    System.out.println("您选择了修改用户名功能");
                    if ("admin".equals(user.getUserName())) {
                        System.out.println("管理员不允许修改用户名！");
                        break;
                    }
                    ud.updateUserName(users, user, domUsers, sc);
                    break;
                case "F":
                    System.out.println("您选择了修改密码功能");
                    ud.updatePassword(users, user, domBooks, sc);
                    break;
                case "G":
                    if ("admin".equals(user.getUserName())) {
                        System.out.println("您选择了修改用户功能");
                        ud.updateUser(users, domBooks, sc);
                        break;
                    } else {
                        System.out.println("您选择的功能不存在，请重新选择……");
                        break;
                    }
                case "H":
                    if ("admin".equals(user.getUserName())) {
                        System.out.println("您选择了删除用户功能");
                        ud.deleteUser(users, domBooks, sc);
                    } else {
                        System.out.println("您选择的功能不存在，请重新选择……");
                        break;
                    }
                case "I":
                    System.out.println("您选择了查看所有用户功能");
                    ud.showUsers(users);
                    break;
                case "Q":
                    System.out.println("您选择了注销功能");
                    System.out.println("正在注销……");
                    System.out.println("注销成功");
                    sysMenu(users, books, domUsers, domBooks, sc);
                    break;
                default:
                    System.out.println("您选择的功能不存在，请重新选择……");
            }
        }
    }
    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 用户登录功能
     * @Date : 2019/01/23/0023 7:31
     * @Param : [users, books]
     */
    public void login(ArrayList<Users> users, ArrayList<Books> books, Document domUsers, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        System.out.println("您选择了登录功能……");
        Users user = new Users();
        while (true) {
            System.out.println("请输入用户名：");
            //获取用户名
            String userName = sc.nextLine();
            if ("Q".equals(userName.toUpperCase())) {
                System.out.println("返回上一级");
                return;
            }
            user.setUserName(userName);
            if (users.contains(user)) {
                while (true) {
                    System.out.println("请输入密码：");
                    //获取密码
                    String password = sc.nextLine();
                    for (Users user1 : users) {
                        if (user1.getUserName().equals(userName)) {
                            if (user1.getPassWord().equals(password)) {
                                user.setPassWord(password);
                                if (!"admin".equals(userName)) {
                                    System.out.println("欢迎登陆，" + userName);
                                    menuNor(users, books, user, "", "", domUsers, domBooks, sc);
                                } else {
                                    System.out.println("欢迎登陆，管理员");
                                    menuNor(users, books, user, "修改用户(G) ", "删除用户(H) ", domUsers, domBooks, sc);
                                }
                            }
                            System.out.println("密码输入错误，请重新输入……");
                        }
                    }
                }
            }
            System.out.println("该用户不存在，请重新输入用户名：");
            System.out.println("输入Q返回上一级....");
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 用户注册功能
     * @Date : 2019/01/23/0023 7:30
     * @Param : [users, books]
     */
    public void register(ArrayList<Users> users, Document domUsers, Scanner sc) throws IllegalAccessException, IOException {
        System.out.println("您选择了注册用户功能……");
        //用户
        Users user = new Users();
        while (true) {
            System.out.println("请输入用户名(用户名由字母数字组成2~16位)……");
            //获取用户名
            String userName = sc.nextLine();
            if (userName.matches(GlobalContants.NAME_REG)) {
                user.setUserName(userName);
                if (!users.contains(user)) {
                    while (true) {
                        System.out.println("请输入密码（密码由字母数字特殊符号组成6~16位，字母至少出现一次）……");
                        String pass = sc.nextLine();
                        if (pass.matches(GlobalContants.PASS_REG)) {
                            user.setPassWord(pass);
                            users.add(user);
                            Dom4jUtils.addElement(user, domUsers);
                            System.out.println("添加用户成功！！！");
                            return;
                        } else {
                            System.out.println("输入的密码不符合要求，请重新输入……");
                        }
                    }
                }
                System.out.println("该用户名已被注册，请重新输入");
            } else {
                System.out.println("输入的用户名不符合要求，请重新输入……");
            }
        }
    }
}
