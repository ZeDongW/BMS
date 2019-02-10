package com.wzd.test;

import com.wzd.entity.Books;
import com.wzd.entity.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : Test
 * @description：测试类
 * @modified By：
 * @date ：Created in 2019/01/24/0024 11:16
 */
public class Test {
    public static void main(String[] args) {
        HashSet<Users> users = new HashSet<Users>();
        users.add(new Users("wzdlol","12345"));
        users.add(new Users("wzdlol1","12345"));
        users.add(new Users("wzdlol2","12345"));
        users.add(new Users("wzdlol3","12345"));
        users.add(new Users("wzdlol","skjdhfkjhda"));
        System.out.println(users);
        Users users1 = new Users();
        users1.setUserName("wzdlol");
        System.out.println(users.contains(users1));

        ArrayList<Books> books = new ArrayList<>();
        books.add(new Books("java","123","出版社",89.12,213412,new Date()));
        books.add(new Books("javaEE","123","出版社",89.12,213412,new Date()));
        Iterator<Books> iterator = books.iterator();
        while (iterator.hasNext()){
            Books book = iterator.next();
            if ("javaEE".equals(book.getBookName())){
                iterator.remove();
            }
        }
        System.out.println(books);
    }
}
