package cn.zedongw.bms.utils.comparator.book;

import cn.zedongw.bms.entity.Books;

import java.util.Comparator;

/**
 * @Author ZeDongW
 * @ClassName ComparatorByBookName
 * @Description 根据书本名排序
 * @Version 1.0
 * @date ：Created in 2019/5/13 0013 6:58
 * @modified By：
 */

public class ComparatorByBookName implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return o1.getBookName().compareTo(o2.getBookName());
    }
}
