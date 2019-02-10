package com.wzd.comparator;

import com.wzd.entity.Books;

import java.util.Comparator;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : ComparatorByPrice
 * @description：图书管理系统---图书自定义比较器---根据书名
 * @modified By：
 * @date ：Created in 2019/01/22/0022 23:00
 */
public class ComparatorByPrice implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return o1.getBookName().compareTo(o2.getBookName());
    }
}
