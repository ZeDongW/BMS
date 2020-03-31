package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.utils.Dom4jUtils;
import cn.zedongw.bms.utils.GlobalContants;
import cn.zedongw.bms.utils.comparator.ComparatorByAuthor;
import cn.zedongw.bms.utils.comparator.ComparatorByPrice;
import cn.zedongw.bms.utils.comparator.ComparatorByPublishDate;
import org.dom4j.Document;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @Author ZeDongW
 * @ClassName BooksDao
 * @Description Books增删改查
 * @Version 1.0
 * @date ：Created in 2019/4/20 0020 7:31
 * @modified By：
 */

public class BooksDaoImpl implements IBooksDao {

    /**
     * @return : void
     * @Author : ZeDongW
     * @Description: 添加图书
     * @Date : 2019/02/01/0001 17:32
     * @Param : [books, sc]
     */
    @Override
    public void addBook(ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        Books book = new Books();
        System.out.println("请输入书名：");
        String bookName = sc.nextLine();
        System.out.println("请输入作者：");
        String bookAuthor = sc.nextLine();
        System.out.println("请输入出版社：");
        String publisher = sc.nextLine();
        Double price = null;
        while (true) {
            System.out.println("请输入价格：");
            try {
                price = Double.valueOf(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("您输入的价格有误，请重新输入......");
            }
        }
        Integer bookNum = null;
        while (true) {
            System.out.println("请输入书号：");
            try {
                bookNum = Integer.valueOf(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("您输入的书号有误，请重新输入......");
            }
        }
        Date publishDate = null;
        while (true) {
            System.out.println("请输入出版日期：（格式为：yyyy-MM-dd）");
            try {
                publishDate = GlobalContants.SIMPLE_DATE_FORMAT.parse(sc.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("您输入的日期格式有误！！！");
            }
        }
        System.out.println("新书信息如下： 书名：" + bookName + " 作者：" + bookAuthor + " 出版社：" + publisher +
                " 价格：" + price + " 书号:" + bookNum + " 出版日期：" + GlobalContants.SIMPLE_DATE_FORMAT.format(publishDate));
        while (true) {
            System.out.println("是否保存该书？ 是(Y) 否(N)");
            String yesOrNo = sc.nextLine().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                book.setBookName(bookName);
                book.setBookAuthor(bookAuthor);
                book.setPublisher(publisher);
                book.setPrice(price);
                book.setBookNum(bookNum);
                book.setPublishDate(publishDate);
                books.add(book);
                Dom4jUtils.addElement(book, domBooks);
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
     * @Author     : ZeDongW
     * @Description: 删除图书
     * @Date       : 2019/02/03/0003 22:06
     * @Param      : [books, sc]
     * @return     : void
     */
    @Override
    public void deleteBook(ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        Iterator<Books> it = books.iterator();
        while (true) {
            System.out.println("请输入要删除的操作：(A)按书名删除  (B)按作者删除  (C)按书号删除 (E)返回上一级");
            String choose = sc.nextLine().toUpperCase();
            if ("A".equals(choose)){
                System.out.println("请输入作者：");
                String bookAuthor = sc.nextLine();
                while (it.hasNext()){
                    Books book = it.next();
                    if (bookAuthor.equals(book.getBookAuthor())){
                        removeBook(it, book, domBooks, sc);
                        return;
                    }
                }
            }
            if ("B".equals(choose)){
                System.out.println("请输入书名：");
                String bookName = sc.nextLine();
                while (it.hasNext()){
                    Books book = it.next();
                    if (bookName.equals(book.getBookName())){
                        removeBook(it, book, domBooks, sc);
                        return;
                    }
                }
            }
            if ("C".equals(choose)){
                while (true) {
                    System.out.println("请输入书号：");
                    int bookNum = 0;
                    try {
                        bookNum = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("您输入的书号格式有误，请重新输入.......");
                    }
                    while (it.hasNext()){
                        Books book = it.next();
                        if (bookNum == book.getBookNum()){
                            removeBook(it, book, domBooks, sc);
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

    /**
     * @Author: ZeDongW
     * @Description: 删除书本
     * @Date: 2019/4/20 0020 7:33
     * @Param: [it, sc, book, sdf]
     * @return: void
     */
    @Override
    public void removeBook(Iterator it, Books book, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        showBook(book);
        while (true) {
            System.out.println("确定删除吗？ 是(Y) 否(N)");
            String yesOrNo = sc.nextLine().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在删除……");
                it.remove();
                Dom4jUtils.removeElement(book, domBooks);

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
     * @Param : [books, sc]
     */
    @Override
    public void showBooks(ArrayList<Books> books, Scanner sc) throws IOException {
        System.out.println("默认排序：");
        shortBooks(books, null);
        while (true) {
            System.out.println("请选择查看方式： (A)价格排序 (B)作者排序 (C)出版日期排序 (E)返回上一级");
            String choose = sc.nextLine().toUpperCase();
            if("A".equals(choose)){
                System.out.println("按价格排序：");
                shortBooks(books, new ComparatorByPrice());
            } else if ("B".equals(choose)){
                shortBooks(books, new ComparatorByAuthor());
            } else if ("C".equals(choose)){
                shortBooks(books, new ComparatorByPublishDate());
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
    @Override
    public void shortBooks(ArrayList<Books> books, Comparator comp) {
        if (comp != null){
            Collections.sort(books,comp);
        }
        System.out.println("|书名|\t\t\t|作者|\t\t\t|出版社|\t\t\t|价格|\t\t\t|书号|\t\t\t|出版日期|");
        books.forEach(book ->{
            System.out.println(book.getBookName() + "\t\t\t" + book.getBookAuthor() + "\t\t\t" + book.getPublisher()
                    + "\t\t\t" + book.getPrice() + "\t\t\t" + book.getBookNum() + "\t\t\t" + GlobalContants.SIMPLE_DATE_FORMAT.format(book.getPublishDate()));
        });
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改书本信息
     * @Date       : 2019/02/06/0006 16:23
     * @Param      : [books, sc]
     * @return     : void
     */
    @Override
    public void updateBook(ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException {
        while (true){
            System.out.println("请输入书号：");
            try {
                int bookNum = Integer.valueOf(sc.nextLine());
                for (Books book : books) {
                    if (bookNum == book.getBookNum()){
                        showBook(book);
                        while (true) {
                            System.out.println("请输入要修改的属性： (A)修改书名  (B)修改作者  (C)修改出版社  (D)修改价格  (E)修改书号  (F)修改出版日期  (G)返回上一级");
                            String choose = sc.nextLine().toUpperCase();
                            if ("A".equals(choose)){
                                System.out.println("您选择了修改书名功能：");
                                updateBookName(book, books, domBooks, sc);
                            }else if ("B".equals(choose)){
                                System.out.println("您选择了修改作者功能：");
                                updateBookAuthor(book, books, domBooks, sc);
                            }else if ("C".equals(choose)){
                                System.out.println("您选择了修改出版社功能：");
                                updatePublisher(book, books, domBooks, sc);
                            }else if ("D".equals(choose)){
                                System.out.println("您选择了修改价格功能：");
                                updatePrice(book, books, domBooks, sc);
                            }else if ("E".equals(choose)){
                                System.out.println("您选择了修改书号功能：");
                                updateBookNum(book, books, domBooks, sc);
                            }else if ("F".equals(choose)){
                                System.out.println("您选择了修改出版日期功能：");
                                updatePublishDate(book, books,domBooks, sc);
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
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author     : ZeDongW
     * @Description: 修改出版日期
     * @Date       : 2019/02/11/0011 7:10
     * @Param      : [book, books, sc]
     * @return     : void
     */
    @Override
    public void updatePublishDate(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException {
        while (true) {
            System.out.println("请输入新的出版日期：（格式为：yyyy-MM-dd）");
            try {
                Date newPublishDate = GlobalContants.SIMPLE_DATE_FORMAT.parse(sc.nextLine());
                while (true){
                    System.out.println("确定修改出版日期吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.nextLine().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在修改出版日期……");
                        Books book1 = book;
                        books.remove(book);
                        book1.setPublishDate(newPublishDate);
                        books.add(book1);
                        Dom4jUtils.removeElement(book, domBooks);
                        Dom4jUtils.addElement(book1, domBooks);
                        System.out.println("修改出版日期成功！！！");
                        System.out.println("书本信息如下：");
                        showBook(book1);
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
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
    @Override
    public void updateBookNum(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException {
        while (true) {
            System.out.println("请输入新的书号：");
            try {
                int newBookNum = Integer.valueOf(sc.nextLine());
                while (true){
                    System.out.println("确定修改书号吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.nextLine().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在修改书号……");
                        Books book1 = book;
                        books.remove(book);
                        book1.setBookNum(newBookNum);
                        books.add(book1);
                        Dom4jUtils.removeElement(book, domBooks);
                        Dom4jUtils.addElement(book1, domBooks);
                        System.out.println("修改书号成功！！！");
                        System.out.println("书本信息如下：");
                        showBook(book1);
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
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
    @Override
    public void updatePrice(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException {
        while (true) {
            System.out.println("请输入新的价格：");
            try {
                Double newPrice = Double.valueOf(sc.nextLine());
                while (true){
                    System.out.println("确定修改价格吗？ 是(Y) 否(N)");
                    String yesOrNo = sc.nextLine().toUpperCase();
                    if ("Y".equals(yesOrNo)) {
                        System.out.println("正在修改价格……");
                        Books book1 = book;
                        books.remove(book);
                        book1.setPrice(newPrice);
                        books.add(book1);
                        Dom4jUtils.removeElement(book, domBooks);
                        Dom4jUtils.addElement(book1, domBooks);
                        System.out.println("修改价格成功！！！");
                        System.out.println("书本信息如下：");
                        showBook(book1);
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
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
    @Override
    public void updatePublisher(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        System.out.println("请输入新的出版社：");
        String newPublisher = sc.nextLine();
        while (true){
            System.out.println("确定修改出版社吗？ 是(Y) 否(N)");
            String yesOrNo = sc.nextLine().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在修改出版社……");
                Books book1 = book;
                books.remove(book);
                book1.setPublisher(newPublisher);
                books.add(book1);
                Dom4jUtils.removeElement(book, domBooks);
                Dom4jUtils.addElement(book1, domBooks);
                System.out.println("修改出版社成功！！！");
                System.out.println("书本信息如下：");
                showBook(book1);
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
    @Override
    public void updateBookAuthor(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        System.out.println("亲输入新的作者名");
        String newBookAuthor = sc.nextLine();
        while (true){
            System.out.println("确定修改作者名吗？ 是(Y) 否(N)");
            String yesOrNo = sc.nextLine().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在修改作者名……");
                Books book1 = book;
                books.remove(book);
                book1.setBookAuthor(newBookAuthor);
                books.add(book1);
                Dom4jUtils.removeElement(book, domBooks);
                Dom4jUtils.addElement(book1, domBooks);
                System.out.println("修改作者名成功！！！");
                System.out.println("书本信息如下：");
                showBook(book1);
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
    @Override
    public void updateBookName(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException {
        System.out.println("请输入修新的书名：");
        String newBookName = sc.nextLine();
        while (true){
            System.out.println("确定修改书名吗？ 是(Y) 否(N)");
            String yesOrNo = sc.nextLine().toUpperCase();
            if ("Y".equals(yesOrNo)) {
                System.out.println("正在修改书名……");
                Books book1 = book;
                books.remove(book);
                book1.setBookName(newBookName);
                books.add(book1);
                Dom4jUtils.removeElement(book, domBooks);
                Dom4jUtils.addElement(book1, domBooks);
                System.out.println("修改书名成功！！！");
                System.out.println("书本信息如下：");
                showBook(book1);
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
     * @Param      : [book]
     * @return     : void
     */
    @Override
    public void showBook(Books book) {
        System.out.println("|书名|\t\t\t|作者|\t\t\t|出版社|\t\t\t|价格|\t\t\t|书号|\t\t\t|出版日期|");
        System.out.println(book.getBookName() + "\t\t\t" + book.getBookAuthor() + "\t\t\t" + book.getPublisher()
                + "\t\t\t" + book.getPrice() + "\t\t\t" + book.getBookNum() + "\t\t\t" + GlobalContants.SIMPLE_DATE_FORMAT.format(book.getPublishDate()));
    }
}
