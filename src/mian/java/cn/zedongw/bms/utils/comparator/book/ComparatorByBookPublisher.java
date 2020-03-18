package cn.zedongw.bms.utils.comparator.book;

import cn.zedongw.bms.entity.Books;

import java.util.Comparator;

/**
 * @Author ZeDongW
 * @ClassName ComparatorByBookPublisher
 * @Description 根据书本出版社排序
 * @Version 1.0
 * @date ：Created in 2019/5/13 0013 7:01
 * @modified By：
 */

public class ComparatorByBookPublisher implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return o1.getPublisher().compareTo(o2.getPublisher());
    }
}
