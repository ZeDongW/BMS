package cn.zedongw.bms.utils.comparator.book;

import cn.zedongw.bms.entity.Books;

import java.util.Comparator;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : ComparatorByBookPrice
 * @description：图书管理系统---图书自定义比较器---根据书名
 * @modified By：
 * @date ：Created in 2019/01/22/0022 23:00
 */
public class ComparatorByBookPrice implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
