package cn.zedongw.bms.app;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : Application
 * @description：图书管理系统入口
 * @modified By：
 * @date ：Created in 2019/01/23/0023 22:38
 */
public class Application {
    public static void main(String[] args) {
        //存储用户的list
        HashSet<Users> users = new HashSet<Users>();
        //存储书本的list
        ArrayList<Books> books = new ArrayList<Books>();
        //初始化管理员用户
        users.add(new Users("admin","q123456"));
        //获取用户的输入信息
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BookManagerSystem bookManagerSystem = new BookManagerSystem();
        bookManagerSystem.app(users, books, sdf, sc);
    }
}
