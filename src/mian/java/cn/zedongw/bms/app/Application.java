package cn.zedongw.bms.app;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.Dom4jUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ：ZeDongW
 * @version : 1.1
 * @ClassName : Application
 * @description：图书管理系统入口
 * @modified By：ZeDongW
 * @date ：Created in 2019/01/23/0023 22:38
 *         Modified in  2019/04/19/0023 7:37
 */
public class Application {
    public static void main(String[] args) throws DocumentException, IOException {
        cn.zedongw.bms.app.UsersControl usersControl = new cn.zedongw.bms.app.UsersControl();
        Document domUsers = Dom4jUtils.loadXml(Users.class);
        Document domBooks = Dom4jUtils.loadXml(Books.class);
        Scanner sc = new Scanner(System.in);
        try {
            ArrayList<Users> users = Dom4jUtils.getList(Users.class); //存储用户的list
            ArrayList<Books> books = Dom4jUtils.getList(Books.class); //存储书本的list
            Users admin = new Users("admin", "123456");
            if (!users.contains(admin)) {
                Dom4jUtils.addElement(admin, domUsers);
                users.add(admin);
            }
            usersControl.sysMenu(users, books, domUsers, domBooks, sc);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            Dom4jUtils.writeXml(Users.class, domUsers);
            Dom4jUtils.writeXml(Books.class, domBooks);
        }
    }
}
