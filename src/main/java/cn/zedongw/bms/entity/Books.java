package cn.zedongw.bms.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : Books
 * @description： 图书管理系统书本实体类
 * @modified By：
 * @date ：Created in 2019/01/22/0022 22:44
 */
public class Books {

    /**
     * id
     */
    private String id;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String bookAuthor;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 价格
     */
    private double price;

    /**
     * 书号
     */
    private int bookNum;

    /**
     * 出版日期
     */
    private Date publishDate;

    public Books() {
    }

    public Books(String id, String bookName, String bookAuthor, String publisher, double price, int bookNum, Date publishDate) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publisher = publisher;
        this.price = price;
        this.bookNum = bookNum;
        this.publishDate = publishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Books books = (Books) o;
        return Double.compare(books.price, price) == 0 &&
                bookNum == books.bookNum &&
                Objects.equals(bookName, books.bookName) &&
                Objects.equals(bookAuthor, books.bookAuthor) &&
                Objects.equals(publisher, books.publisher) &&
                Objects.equals(publishDate, books.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, bookAuthor, publisher, price, bookNum, publishDate);
    }

    @Override
    public String toString() {
        return "Books{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", bookNum=" + bookNum +
                ", publishDate=" + publishDate +
                '}';
    }
}
