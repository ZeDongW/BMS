package cn.zedongw.bms.utils.comparator.book;

import cn.zedongw.bms.entity.Books;

import java.util.Comparator;

/**
 * @Author ZeDongW
 * @ClassName ComparatorByBookNum
 * @Description 根据书本号排序
 * @Version 1.0
 * @date ：Created in 2019/5/13 0013 7:03
 * @modified By：
 */

public class ComparatorByBookNum implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return Integer.compare(o1.getBookNum(),o2.getBookNum());
    }
}
