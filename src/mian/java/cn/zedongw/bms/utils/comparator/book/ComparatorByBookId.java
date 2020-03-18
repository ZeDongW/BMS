package cn.zedongw.bms.utils.comparator.book;

import cn.zedongw.bms.entity.Books;

import java.util.Comparator;

/**
 * @Author ZeDongW
 * @ClassName ComparatorByBookId
 * @Description 根据书本编号自定义排序
 * @Version 1.0
 * @date ：Created in 2019/5/13 0013 6:56
 * @modified By：
 */

public class ComparatorByBookId implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
