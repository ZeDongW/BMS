package cn.zedongw.bms.app;

import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.comparator.ComparatorByAuthor;
import cn.zedongw.bms.utils.comparator.ComparatorByPrice;
import cn.zedongw.bms.utils.comparator.ComparatorByPublishDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : BookManagerSystem
 * @description：图书管理系统
 * @modified By：
 * @date ：Created in 2019/01/23/0023 7:06
 */
public class BookManagerSystem {
    public void app(HashSet<Users> users, ArrayList<Books> books, SimpleDateFormat sdf, Scanner sc) {
        //用户名正则
        String nameReg = "^[a-zA-Z0-9]{2,16}$";

        //密码正则
        String passReg = "^(?![0-9\\W]+$)[a-zA-Z0-9\\W]{6,16}$";
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
            String operate = sc.next().toUpperCase();

            switch (operate) {
                case "A":
                    login(users, books, sdf, sc, nameReg, passReg);
                    break;
                case "B":
                    register(users, sdf, sc, nameReg, passReg);
                    break;
                case "Q":
                    System.out.println("正在退出……");
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
     * @Description: 用户注册功能
     * @Date : 2019/01/23/0023 7:30
     * @Param : [users, books]
     */
    private void register(HashSet<Users> users, SimpleDateFormat sdf, Scanner sc, String nameReg, String passReg) {
        System.out.println("您选择了注册用户功能……");
        //用户
        Users user = new Users();
        while (true) {
            System.out.println("请输入用户名(用户名由字母数字组成2~16位)……");
            String userName = sc.next();
            if (userName.matches(nameReg)) {
                user.setUserName(userName);
                if (!users.contains(user)) {
                    while (true) {
                        System.out.println("请输入密码（密码由字母数字特殊符号组成6~16位，字母至少出现一次）……");
                        String pass = sc.next();
                        if (pass.matches(passReg)) {
                            user.setPassword(pass);
                            users.add(user);
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

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 用户登录功能
     * @Date : 2019/01/23/0023 7:31
     * @Param : [users, books]
     */
    private void login(HashSet<Users> users, ArrayList<Books> books, SimpleDateFormat sdf, Scanner sc, String nameReg, String passReg) {
        System.out.println("您选择了登录功能……");
        Users user = new Users();
        while (true) {
            System.out.println("请输入用户名：");
            String userName = sc.next();
            if ("Q".equals(userName.toUpperCase())) {
                System.out.println("返回上一级");
                return;
            }
            user.setUserName(userName);
            if (users.contains(user)) {
                while (true) {
                    System.out.println("请输入密码：");
                    String password = sc.next();  
                    for (Users user1 : users) {
                        if (user1.getUserName().equals(userName)) {
                            if (user1.getPassword().equals(password)) {
                                user.setPassword(password);
                                if (!"admin".equals(userName)) {
                                    System.out.println("欢迎登陆，" + userName);
                                    menuNor(users, books, sdf, sc, user, "", "", nameReg, passReg);
                                } else {
                                    System.out.println("欢迎登陆，管理员");
                                    menuNor(users, books, sdf, sc, user, "修改用户(G) ", "删除用户(H) ", nameReg, passReg);
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
     * @Description: 登录后功能菜单
     * @Date : 2019/01/23/0023 21:24
     * @Param : [users, books, sdf, sc, s, s1]
     */
    private void menuNor(HashSet<Users> users, ArrayList<Books> books, SimpleDateFormat sdf, Scanner sc, Users user, String s, String s1, String nameReg, String passReg) {
        while (true) {
            System.out.println("请选择功能： 查看图书(A) 添加图书(B) 修改图书(C) 删除图书(D)");
            System.out.println("修改用户名(E) 修改密码(F) " + s + s1);
            System.out.println("查看所有用户(I) 注销(Q)");
            System.out.println("请输入您选择的功能：");
            String operate = sc.next().toUpperCase();
            switch (operate) {
                case "A":
                    System.out.println("您选择了查看图书功能");
                    showBooks(books, sdf, sc);
                    break;
                case "B":
                    System.out.println("您选择了添加图书功能");
                    addBook(books, sdf, sc);
                    break;
                case "C":
                    System.out.println("您选择了修改图书功能");
                    updateBook(books, sdf, sc);
                    break;
                case "D":
                    System.out.println("您选择了删除图书功能");
                    deleteBook(books, sc, sdf);
                    break;
                case "E":
                    System.out.println("您选择了修改用户名功能");
                    updateUserName(users, sc, user, nameReg);
                    break;
                case "F":
                    System.out.println("您选择了修改密码功能");
                    updatePassword(users, sc, user, passReg);
                    break;
                case "G":
                    if ("admin".equals(user.getUserName())) {
                        System.out.println("您选择了修改用户功能");
                        updateUser(users, sc, nameReg, passReg);
                        break;
                    } else {
                        System.out.println("您选择的功能不存在，请重新选择……");
                        break;
                    }
                case "H":
                    if ("admin".equals(user.getUserName())) {
                        System.out.println("您选择了删除用户功能");
                        deleteUser(users, sc);
                    } else {
                        System.out.println("您选择的功能不存在，请重新选择……");
                        break;
                    }
                case "I":
                    System.out.println("您选择了查看所有用户功能");
                    showUsers(users);
                    break;
                case "Q":
                    System.out.println("您选择了注销功能");
                    System.out.println("正在注销……");
                    System.out.println("注销成功");
                    app(users, books, sdf, sc);
                    break;
                default:
                    System.out.println("您选择的功能不存在，请重新选择……");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 删除图书
     * @Date       : 2019/02/03/0003 22:06
     * @Param      : [books, sc]
     * @return     : void
     */
    private void deleteBook(ArrayList<Books> books, Scanner sc, SimpleDateFormat sdf) {
        Iterator<Books> it = books.iterator();
        while (true) {
            System.out.println("请输入要删除的操作：(A)按书名删除  (B)按作者删除  (C)按书号删除 (E)返回上一级");
            String choose = sc.next().toUpperCase();
            if ("A".equals(choose)){
                System.out.println("请输入作者：");
                String bookAuthor = sc.next();
                while (it.hasNext()){
                    Books book = it.next();
                    if (bookAuthor.equals(book.getBookAuthor())){
                        removeBook(it, sc, book, sdf);
                        return;
                    }
                }
            }
            if ("B".equals(choose)){
                System.out.println("请输入书名：");
                String bookName = sc.next();
                while (it.hasNext()){
                    Books book = it.next();
                    if (bookName.equals(book.getBookName())){
                        removeBook(it, sc, book, sdf);
                        return;
                    }
                }
            }
            if ("C".equals(choose)){
                while (true) {
                    System.out.println("请输入书号：");
                    int bookNum = 0;
                    try {
                        bookNum = Integer.parseInt(sc.next());
                    } catch (NumberFormatException e) {
                        System.out.println("您输入的书号格式有误，请重新输入.......");
                    }
                    while (it.hasNext()){
                        Books book = it.next();
                        if (bookNum == book.getBookNum()){
                            removeBook(it, sc, book, sdf);
                            return;
                        }
                    }
                }
            }
            if ("E".equals(choose)){
                System.out.println("返回上一级......");
                return;
            }
        }
    }

    private void removeBook(Iterator it, Scanner sc, Books book, SimpleDateFormat sdf){
        showBook(sdf, book);
        while (true) {
            System.out.println("确定删除吗？ 是(Y) 否(N)");
            String yesOrNo = sc.next().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在删除……");
                it.remove();
                System.out.println("删除成功！！！");
                return;
            } else if ("N".equals(yesOrNo)) {
                System.out.println("取消删除成功");
                return;
            } else {
                System.out.println("您的输入有误，请重新输入：");
            }
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 根据不同的排序方式对书本进行排序
     * @Date : 2019/02/03/0003 17:40
     * @Param : [books, sdf, sc]
     */
    private void showBooks(ArrayList<Books> books, SimpleDateFormat sdf, Scanner sc) {
        System.out.println("默认排序：");
        shortBooks(books, sdf, null);
        while (true) {
            System.out.println("请选择查看方式： (A)价格排序 (B)作者排序 (C)出版日期排序 (E)返回上一级");
            String choose = sc.next().toUpperCase();
            if("A".equals(choose)){
                System.out.println("按价格排序：");
                shortBooks(books, sdf, new ComparatorByPrice());
            } else if ("B".equals(choose)){
                shortBooks(books, sdf, new ComparatorByAuthor());
            } else if ("C".equals(choose)){
                shortBooks(books, sdf, new ComparatorByPublishDate());
            } else if ("E".equals(choose)){
                return;
            } else {
                System.out.println("没有对应功能，请重新选择：");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 根据传入比较器对书本排序并输出
     * @Date       : 2019/02/03/0003 18:22
     * @Param      : [books, comparatorByPrice]
     * @return     : void
     */
    private void shortBooks(ArrayList<Books> books, SimpleDateFormat sdf, Comparator comp) {
        if (comp != null){
            Collections.sort(books,comp);
        }
        System.out.println("|书名|\t\t\t|作者|\t\t\t|出版社|\t\t\t|价格|\t\t\t|书号|\t\t\t|出版日期|");
        books.forEach(book ->{
            System.out.println(book.getBookName() + "\t\t\t" + book.getBookAuthor() + "\t\t\t" + book.getPublisher()
                    + "\t\t\t" + book.getPrice() + "\t\t\t" + book.getBookNum() + "\t\t\t" + sdf.format(book.getPublishDate()));
        });
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改书本信息
     * @Date       : 2019/02/06/0006 16:23
     * @Param      : [books, sdf, sc]
     * @return     : void
     */
    private void updateBook(ArrayList<Books> books, SimpleDateFormat sdf, Scanner sc) {
        int bookNum= 0;
        while (true){
            System.out.println("请输入书号：");
            try {
                bookNum = Integer.valueOf(sc.next());
                for (Books book : books) {
                    if (bookNum == book.getBookNum()){
                        showBook(sdf, book);
                        while (true) {
                            System.out.println("请输入要修改的属性： (A)修改书名  (B)修改作者  (C)修改出版社  (D)修改价格  (E)修改书号  (F)修改出版日期  (G)返回上一级");
                            String choose = sc.next().toUpperCase();
                            if ("A".equals(choose)){
                                System.out.println("您选择了修改书名功能：");
                                updateBookName(book, books, sc, sdf);
                            }else if ("B".equals(choose)){
                                System.out.println("您选择了修改作者功能：");
                                updateBookAuthor(book, books, sc, sdf);
                            }else if ("C".equals(choose)){
                                System.out.println("您选择了修改出版社功能：");
                                updatePublisher(book, books, sc, sdf);
                            }else if ("D".equals(choose)){
                                System.out.println("您选择了修改价格功能：");
                                updatePrice(book, books, sc, sdf);
                            }else if ("E".equals(choose)){
                                System.out.println("您选择了修改书号功能：");
                                updateBookNum(book, books, sc, sdf);
                            }else if ("F".equals(choose)){
                                System.out.println("您选择了修改出版日期功能：");
                                updatePublishDate(book, books, sc, sdf);
                            }else if ("G".equals(choose)){
                                System.out.println("正在返回");
                                return;
                            }else{
                                System.out.println("没有对应功能，请重新选择：");
                            }

                        }
                    }
                }
                System.out.println("您输入的书号不存在，请重新输入......");
            } catch (NumberFormatException e) {
                System.out.println("您输入的书号有误，请重新输入......");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改出版日期
     * @Date       : 2019/02/11/0011 7:10
     * @Param      : [book, books, sc, sdf]
     * @return     : void
     */
    private void updatePublishDate(Books book, ArrayList<Books> books, Scanner sc, SimpleDateFormat sdf) {
        while (true) {
            System.out.println("请输入新的出版日期：（格式为：yyyy-MM-dd）");
            try {
                Date newPublishDate = sdf.parse(sc.next());
                while (true){
                    System.out.println("确定修改出版日期吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.next().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在修改出版日期……");
                        Books book1 = book;
                        books.remove(book);
                        book1.setPublishDate(newPublishDate);
                        books.add(book1);
                        System.out.println("修改出版日期成功！！！");
                        System.out.println("书本信息如下：");
                        showBook(sdf, book1);
                        return;
                    } else if ("N".equals(yesOrNo)) {
                        System.out.println("取消修改出版日期成功");
                        return;
                    } else {
                        System.out.println("您的输入有误，请重新输入：");
                    }
                }
            } catch (ParseException e) {
                System.out.println("您输入的日期格式有误！！！");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改书号成功
     * @Date       : 2019/02/11/0011 7:06
     * @Param      : [book, books, sc]
     * @return     : void
     */
    private void updateBookNum(Books book, ArrayList<Books> books, Scanner sc, SimpleDateFormat sdf) {
        while (true) {
            System.out.println("请输入新的书号：");
            try {
                int newBookNum = Integer.valueOf(sc.next());
                while (true){
                    System.out.println("确定修改书号吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.next().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在修改书号……");
                        Books book1 = book;
                        books.remove(book);
                        book1.setBookNum(newBookNum);
                        books.add(book1);
                        System.out.println("修改书号成功！！！");
                        System.out.println("书本信息如下：");
                        showBook(sdf, book1);
                        return;
                    } else if ("N".equals(yesOrNo)) {
                        System.out.println("取消修改书号成功");
                        return;
                    } else {
                        System.out.println("您的输入有误，请重新输入：");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("您输入的书号有误，请重新输入......");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改书本价格
     * @Date       : 2019/02/11/0011 7:02
     * @Param      : [book, books, sc]
     * @return     : void
     */
    private void updatePrice(Books book, ArrayList<Books> books, Scanner sc, SimpleDateFormat sdf) {
        while (true) {
            System.out.println("请输入新的价格：");
            try {
                Double newPrice = Double.valueOf(sc.next());
                while (true){
                    System.out.println("确定修改价格吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.next().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在修改价格……");
                        Books book1 = book;
                        books.remove(book);
                        book1.setPrice(newPrice);
                        books.add(book1);
                        System.out.println("修改价格成功！！！");
                        System.out.println("书本信息如下：");
                        showBook(sdf, book1);
                        return;
                    } else if ("N".equals(yesOrNo)) {
                        System.out.println("取消修改价格成功");
                        return;
                    } else {
                        System.out.println("您的输入有误，请重新输入：");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("您输入的价格有误，请重新输入......");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改出本社
     * @Date       : 2019/02/11/0011 6:55
     * @Param      : [book, books, sc]
     * @return     : void
     */
    private void updatePublisher(Books book, ArrayList<Books> books, Scanner sc, SimpleDateFormat sdf) {
        System.out.println("请输入新的出版社：");
        String newPublisher = sc.next();
        while (true){
            System.out.println("确定修改出版社吗？ 是(Y) 否(N)");
            String yesOrNo = sc.next().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在修改出版社……");
                Books book1 = book;
                books.remove(book);
                book1.setPublisher(newPublisher);
                books.add(book1);
                System.out.println("修改出版社成功！！！");
                System.out.println("书本信息如下：");
                showBook(sdf, book1);
                return;
            } else if ("N".equals(yesOrNo)) {
                System.out.println("取消修改出版社成功");
                return;
            } else {
                System.out.println("您的输入有误，请重新输入：");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改书本作者名
     * @Date       : 2019/02/11/0011 6:44
     * @Param      : [book, books, sc]
     * @return     : void
     */
    private void updateBookAuthor(Books book, ArrayList<Books> books, Scanner sc, SimpleDateFormat sdf) {
        System.out.println("亲输入新的作者名");
        String newBookAuthor = sc.next();
        while (true){
            System.out.println("确定修改作者名吗？ 是(Y) 否(N)");
            String yesOrNo = sc.next().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在修改作者名……");
                Books book1 = book;
                books.remove(book);
                book1.setBookAuthor(newBookAuthor);
                books.add(book1);
                System.out.println("修改作者名成功！！！");
                System.out.println("书本信息如下：");
                showBook(sdf, book1);
                return;
            } else if ("N".equals(yesOrNo)) {
                System.out.println("取消修改作者名成功");
                return;
            } else {
                System.out.println("您的输入有误，请重新输入：");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改书名
     * @Date       : 2019/02/11/0011 6:36
     * @Param      : [book, books, sc]
     * @return     : void
     */
    private void updateBookName(Books book, ArrayList<Books> books, Scanner sc, SimpleDateFormat sdf) {
        System.out.println("请输入修新的书名：");
        String newBookName = sc.next();
        while (true){
            System.out.println("确定修改书名吗？ 是(Y) 否(N)");
            String yesOrNo = sc.next().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在修改书名……");
                Books book1 = book;
                books.remove(book);
                book1.setBookName(newBookName);
                books.add(book1);
                System.out.println("修改书名成功！！！");
                System.out.println("书本信息如下：");
                showBook(sdf, book1);
                return;
            } else if ("N".equals(yesOrNo)) {
                System.out.println("取消修改书名成功");
                return;
            } else {
                System.out.println("您的输入有误，请重新输入：");
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 查看单本书籍信息
     *
     * @Date       : 2019/02/07/0007 15:36
     * @Param      : [sdf, book]
     * @return     : void
     */
    private void showBook(SimpleDateFormat sdf, Books book) {
        System.out.println("|书名|\t\t\t|作者|\t\t\t|出版社|\t\t\t|价格|\t\t\t|书号|\t\t\t|出版日期|");
        System.out.println(book.getBookName() + "\t\t\t" + book.getBookAuthor() + "\t\t\t" + book.getPublisher()
                + "\t\t\t" + book.getPrice() + "\t\t\t" + book.getBookNum() + "\t\t\t" + sdf.format(book.getPublishDate()));
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 添加图书
     * @Date : 2019/02/01/0001 17:32
     * @Param : [books, sdf, sc]
     */
    private void addBook(ArrayList<Books> books, SimpleDateFormat sdf, Scanner sc) {
        Books book = new Books();
        Date publishDate = null;
        System.out.println("请输入书名：");
        String bookName = sc.next();
        System.out.println("请输入作者：");
        String bookAuthor = sc.next();
        System.out.println("请输入出版社：");
        String publisher = sc.next();
        Double price = null;
        while (true) {
            System.out.println("请输入价格：");
             try {
                price = Double.valueOf(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("您输入的价格有误，请重新输入......");
            }
        }
        Integer bookNum = null;
        while (true) {
            System.out.println("请输入书号：");
            try {
                bookNum = Integer.valueOf(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("您输入的书号有误，请重新输入......");
            }
        }
        while (true) {
            System.out.println("请输入出版日期：（格式为：yyyy-MM-dd）");
            try {
                publishDate = sdf.parse(sc.next());
                break;
            } catch (ParseException e) {
                System.out.println("您输入的日期格式有误！！！");
            }
        }
        System.out.println("新书信息如下： 书名：" + bookName + " 作者：" + bookAuthor + " 出版社：" + publisher +
                " 价格：" + price + " 书号:" + bookNum + " 出版日期：" + sdf.format(publishDate));
        while (true) {
            System.out.println("是否保存该书？ 是(Y) 否(N)");
            String yesOrNo = sc.next().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                book.setBookName(bookName);
                book.setBookAuthor(bookAuthor);
                book.setPublisher(publisher);
                book.setPrice(price);
                book.setBookNum(bookNum);
                book.setPublishDate(publishDate);
                books.add(book);
                System.out.println("保存书本成功！！！");
                return;
            } else if ("N".equals(yesOrNo)) {
                System.out.println("取消保存书本");
                return;
            } else {
                System.out.println("您的输入有误，请重新输入：");
            }
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 查看所有用户
     * @Date : 2019/01/23/0023 22:50
     * @Param : [user, sc]
     */
    private void showUsers(HashSet<Users> users) {
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
    private void deleteUser(HashSet<Users> users, Scanner sc) {
        Users user = new Users();
        while (true) {
            showUsers(users);
            System.out.println("请输入要删除用户的用户名：");
            String userName = sc.next();
            user.setUserName(userName);
            if (users.contains(user)) {
                while (true) {
                    System.out.println("确定删除吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.next().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在删除……");
                        users.remove(user);
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
    private void updateUser(HashSet<Users> users, Scanner sc, String nameReg, String passReg) {
        Users user = new Users();
        String userName;
        while (true) {
            showUsers(users);
            System.out.println("请输入要修改用户的用户名：");
            userName = sc.next();
            user.setUserName(userName);
            if (users.contains(user)) {
                for (Users user1 : users) {
                    if (user1.getUserName().equals(userName)) {
                        while (true) {
                            System.out.println("请输入新的用户名(用户名由字母数字组成2~16位)……");
                            userName = sc.next();
                            if (userName.matches(nameReg)) {
                                user.setUserName(userName);
                                if (!users.contains(user)) {
                                    while (true) {
                                        System.out.println("请输入新的密码（密码由字母数字特殊符号组成6~16位，字母至少出现一次）……");
                                        String pass = sc.next();
                                        if (pass.matches(passReg)) {
                                            while (true) {
                                                System.out.println("确定修改吗？ 是(Y) 否(N)");
                                                String yesOrNo = sc.next().toUpperCase();
                                                if ("Y".equals(yesOrNo)) {
                                                    users.remove(user1);
                                                    user.setPassword(pass);
                                                    users.add(user);
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
        }
    }

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 普通用户修改密码
     * @Date : 2019/01/30/0030 11:28
     * @Param : [users, sc, user, passReg]
     */
    private void updatePassword(HashSet<Users> users, Scanner sc, Users user, String passReg) {
        Users user1 = new Users();
        while (true) {
            System.out.println("请输入新的密码（密码由字母数字特殊符号组成6~16位，字母至少出现一次）……");
            String pass = sc.next();
            if (pass.matches(passReg)) {
                while (true) {
                    System.out.println("确定修改吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.next().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        user1.setUserName(user.getUserName());
                        user1.setPassword(pass);
                        users.remove(user);
                        users.add(user1);
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
    private void updateUserName(HashSet<Users> users, Scanner sc, Users user, String nameReg) {
        Users user1 = new Users();
        while (true) {
            System.out.println("请输入新的用户名(用户名由字母数字组成2~16位)……");
            String userName = sc.next();
            if (userName.matches(nameReg)) {
                user1.setUserName(userName);
                if (!users.contains(user1)) {
                    while (true) {
                        System.out.println("确定修改吗？ 是(Y) 否(N)");
                        String yesOrNo = sc.next().toUpperCase();
                        if ("Y".equals(yesOrNo)) {
                            user1.setPassword(user.getPassword());
                            users.remove(user);
                            users.add(user1);
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

