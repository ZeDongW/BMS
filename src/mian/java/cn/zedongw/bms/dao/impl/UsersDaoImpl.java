package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.Dom4jUtils;
import cn.zedongw.bms.utils.GlobalContants;
import org.dom4j.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author ZeDongW
 * @ClassName UsersDao
 * @Description Users增删改查
 * @Version 1.0
 * @date ：Created in 2019/4/20 0020 7:22
 * @modified By：
 */

public class UsersDaoImpl implements IUsersDao {

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 查看所有用户
     * @Date : 2019/01/23/0023 22:50
     * @Param : [user, sc]
     */
    @Override
    public void showUsers(ArrayList<Users> users) {
        System.out.println("用户名");
        for (Users user : users) {
            System.out.println(user.getUserName());
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 管理员删除用户
     * @Date : 2019/01/23/0023 22:59
     * @Param : [users, sc]
     */
    @Override
    public void deleteUser(ArrayList<Users> users, Document domUsers, Scanner sc) throws IllegalAccessException, IOException {
        Users user = new Users();
        while (true) {
            showUsers(users);
            System.out.println("请输入要删除用户的用户名：");
            String userName = sc.nextLine();
            user.setUserName(userName);
            if (users.contains(user)) {
                while (true) {
                    System.out.println("确定删除吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.nextLine().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在删除……");
                        users.remove(user);
                        Dom4jUtils.removeElement(user, domUsers);
                        System.out.println("删除成功！！！");
                        return;
                    } else if ("N".equals(yesOrNo)) {
                        System.out.println("取消删除成功");
                        return;
                    } else {
                        System.out.println("您的输入有误，请重新输入：");
                    }
                }
            } else {
                System.out.println("该用户不存在，请重新输入：");
            }
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 管理员修改用户
     * @Date : 2019/01/24/0024 11:08
     * @Param : [users, sc]
     */
    @Override
    public void updateUser(ArrayList<Users> users, Document domUsers, Scanner sc) throws IllegalAccessException, IOException {
        Users user = new Users();
        while (true) {
            showUsers(users);
            System.out.println("请输入要修改用户的用户名：");
            String userName = sc.nextLine();
            if(!"admin".equals(userName)) {
                user.setUserName(userName);
                if (users.contains(user)) {
                    for (Users user1 : users) {
                        if (user1.getUserName().equals(userName)) {
                            while (true) {
                                System.out.println("请输入新的用户名(用户名由字母数字组成2~16位)……");
                                userName = sc.nextLine();
                                if (userName.matches(GlobalContants.NAME_REG)) {
                                    user.setUserName(userName);
                                    if (!users.contains(user)) {
                                        while (true) {
                                            System.out.println("请输入新的密码（密码由字母数字特殊符号组成6~16位，字母至少出现一次）……");
                                            String pass = sc.nextLine();
                                            if (pass.matches(GlobalContants.PASS_REG)) {
                                                while (true) {
                                                    System.out.println("确定修改吗？ 是(Y) 否(N)");
                                                    String yesOrNo = sc.nextLine().toUpperCase();
                                                    if ("Y".equals(yesOrNo)) {
                                                        users.remove(user1);
                                                        user.setPassWord(pass);
                                                        users.add(user);
                                                        Dom4jUtils.removeElement(user1,domUsers);
                                                        Dom4jUtils.addElement(user, domUsers);
                                                        System.out.println("修改用户成功");
                                                        return;
                                                    } else if ("N".equals(yesOrNo)) {
                                                        System.out.println("取消修改用户");
                                                        return;
                                                    } else {
                                                        System.out.println("您的输入有误，请重新输入：");
                                                    }
                                                }
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
                } else {
                    System.out.println("该用户不存在，请重新输入：");
                }
            } else {
                System.out.println("管理员不允许修改用户名");
                return;
            }
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 普通用户修改密码
     * @Date : 2019/01/30/0030 11:28
     * @Param : [users, sc, user, passReg]
     */
    @Override
    public void updatePassword(ArrayList<Users> users, Users user, Document domUsers, Scanner sc) throws IllegalAccessException, IOException {
        Users user1 = new Users();
        while (true) {
            System.out.println("请输入新的密码（密码由字母数字特殊符号组成6~16位，字母至少出现一次）……");
            String pass = sc.nextLine();
            if (pass.matches(GlobalContants.PASS_REG)) {
                while (true) {
                    System.out.println("确定修改吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.nextLine().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        user1.setUserName(user.getUserName());
                        user1.setPassWord(pass);
                        users.remove(user);
                        users.add(user1);
                        Dom4jUtils.removeElement(user, domUsers);
                        Dom4jUtils.addElement(user, domUsers);
                        System.out.println("修改密码成功");
                        return;
                    } else if ("N".equals(yesOrNo)) {
                        System.out.println("取消密码用户");
                        return;
                    } else {
                        System.out.println("您的输入有误，请重新输入：");
                    }
                }
            } else {
                System.out.println("输入的密码不符合要求，请重新输入……");
            }
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 普通用户修改用户名
     * @Date : 2019/02/01/0001 11:14
     * @Param : [users, sc, user, nameReg]
     */
    @Override
    public void updateUserName(ArrayList<Users> users, Users user, Document domUsers, Scanner sc) throws IllegalAccessException, IOException {
        Users user1 = new Users();
        while (true) {
            System.out.println("请输入新的用户名(用户名由字母数字组成2~16位)……");
            String userName = sc.nextLine();
            if (userName.matches(GlobalContants.NAME_REG)) {
                user1.setUserName(userName);
                if (!users.contains(user1)) {
                    while (true) {
                        System.out.println("确定修改吗？ 是(Y) 否(N)");
                        String yesOrNo = sc.nextLine().toUpperCase();
                        if ("Y".equals(yesOrNo)) {
                            user1.setPassWord(user.getPassWord());
                            users.remove(user);
                            users.add(user1);
                            Dom4jUtils.removeElement(user, domUsers);
                            Dom4jUtils.addElement(user1, domUsers);
                            System.out.println("修改用户名成功");
                            return;
                        } else if ("N".equals(yesOrNo)) {
                            System.out.println("取消修改用户名");
                            return;
                        } else {
                            System.out.println("您的输入有误，请重新输入：");
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
