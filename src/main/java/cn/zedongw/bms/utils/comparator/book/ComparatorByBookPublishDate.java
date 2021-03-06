package cn.zedongw.bms.utils.comparator.book;

import cn.zedongw.bms.entity.Books;

import java.util.Comparator;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : ComparatorByBookPublishDate
 * @description：图书管理系统---图书自定义比较器---根据出版日期
 * @modified By：
 * @date ：Created in 2019/01/22/0022 23:02
 */
public class ComparatorByBookPublishDate implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return o1.getPublishDate().compareTo(o2.getPublishDate());
    }
}
