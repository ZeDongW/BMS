package cn.zedongw.bms.utils.comparator.book;


import cn.zedongw.bms.entity.Books;

import java.util.Comparator;

/**
 * @author ：ZeDongW
 * @version : 1.0
 * @ClassName : ComparatorByBookAuthor
 * @description：图书馆里系统---图书自定义比较器---根据作者
 * @modified By：
 * @date ：Created in 2019/01/22/0022 23:01
 */
public class ComparatorByBookAuthor implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return o1.getBookAuthor().compareTo(o2.getBookAuthor());
    }
}
