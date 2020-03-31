package cn.zedongw.bms.dao;

import cn.zedongw.bms.entity.Books;
import org.dom4j.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author ZeDongW
 * @Description Books数据访问层接口
 * @Version 1.0
 * @date ：Created in 2019/4/20 0020 7:21
 * @modified By：
 */
public interface IBooksDao {

    /**
     * 功能描述: 添加书本
     * @MethodName: addBook
     * @param books 1
     * @param domBooks 2
     * @param sc 3
     * @throws IllegalAccessException
     * @throws IOException
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:52
     */
    void addBook(ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 功能描述: 删除书本
     * @MethodName: deleteBook
     * @param books 1
     * @param domBooks 2
     * @param sc 3
     * @throws IllegalAccessException
     * @throws IOException
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:50
     */
    void deleteBook(ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 移除书本
     *
     * @param it
     * @param book
     * @param domBooks
     * @param sc
     * @throws IllegalAccessException
     * @throws IOException
     */
    void removeBook(Iterator it, Books book, Document domBooks, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 根据不同的排序方式对书本进行排序
     *
     * @param books
     * @param sc
     * @throws IOException
     * @Author : ZeDongW
     * @Description: 根据不同的排序方式对书本进行排序
     */
    void showBooks(ArrayList<Books> books, Scanner sc) throws IOException;

    /**
     * 功能描述: 根据传入比较器对书本排序并输出
     *
     * @param books
     * @param comp
     * @Author : ZeDongW
     * @Date : 2019/02/03/0003 18:22
     */
    void shortBooks(ArrayList<Books> books, Comparator comp);

    /**
     * 功能描述: 修改书本信息
     *
     * @param books    1
     * @param domBooks 2
     * @param sc       3
     * @throws IOException
     * @MethodName: updateBook
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:54
     */
    void updateBook(ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException;

    /**
     * 功能描述: 修改出版日期
     *
     * @param book     1
     * @param books    2
     * @param domBooks 3
     * @param sc       4
     * @throws IOException
     * @MethodName: updatePublishDate
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:55
     */
    void updatePublishDate(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException;

    /**
     * 功能描述: 修改书号
     *
     * @param book     1
     * @param books    2
     * @param domBooks 3
     * @param sc       4
     * @throws IOException
     * @MethodName: updateBookNum
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:55
     */
    void updateBookNum(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException;

    /**
     * 功能描述: 修改书本价格
     *
     * @param book     1
     * @param books    2
     * @param domBooks 3
     * @param sc       4
     * @throws IOException
     * @MethodName: updatePrice
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:56
     */
    void updatePrice(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IOException;

    /**
     * 功能描述: 修改出版社
     *
     * @param book     1
     * @param books    2
     * @param domBooks 3
     * @param sc       4
     * @throws IllegalAccessException
     * @throws IOException
     * @MethodName: updatePublisher
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:56
     */
    void updatePublisher(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 功能描述: 修改作者
     *
     * @param book     1
     * @param books    2
     * @param domBooks 3
     * @param sc       4
     * @throws IllegalAccessException
     * @throws IOException
     * @MethodName: updateBookAuthor
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:56
     */
    void updateBookAuthor(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 功能描述: 修改书名
     *
     * @param book     1
     * @param books    2
     * @param domBooks 3
     * @param sc       4
     * @throws IllegalAccessException
     * @throws IOException
     * @MethodName: updateBookName
     * @Return: void
     * @Author: ZeDongW
     * @Date: 2020/3/17 0017 22:58
     */
    void updateBookName(Books book, ArrayList<Books> books, Document domBooks, Scanner sc) throws IllegalAccessException, IOException;

    /**
     * 功能描述: 查看单本书籍信息
     * @methodName: showBook
     * @param book 1
     * @throws
     * @return: void
     * @author: ZeDongW
     * @date: 2020/3/17 0017 22:59
     */
    void showBook(Books book);
}
