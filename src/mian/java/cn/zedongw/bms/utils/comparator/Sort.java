package cn.zedongw.bms.utils.comparator;

import cn.zedongw.bms.utils.comparator.book.*;
import cn.zedongw.bms.utils.comparator.user.ComparatorByUserId;
import cn.zedongw.bms.utils.comparator.user.ComparatorByUserName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author ZeDongW
 * @ClassName Sort
 * @Description 比较器
 * @Version 1.0
 * @date ：Created in 2019/5/13 0013 7:13
 * @modified By：
 */

public class Sort {
    public static <T> ArrayList<T> sort(ArrayList<T> list, String sort){
        Comparator comp = null;
        if(sort != null){
            switch (sort){
                case "userId":
                    comp = new ComparatorByUserId();
                    break;
                case "userName":
                    comp = new ComparatorByUserName();
                    break;
                case "bookAuthor":
                    comp = new ComparatorByBookAuthor();
                    break;
                case "bookId":
                    comp = new ComparatorByBookId();
                    break;
                case "bookName":
                    comp = new ComparatorByBookName();
                    break;
                case "bookNum":
                    comp = new ComparatorByBookNum();
                    break;
                case "bookPrice":
                    comp = new ComparatorByBookPrice();
                    break;
                case "bookPublishDate":
                    comp = new ComparatorByBookPublishDate();
                    break;
                case "bookPublisher":
                    comp = new ComparatorByBookPublisher();
                    break;
                default:
                    comp = null;
                    break;
            }
        }
        if (comp != null){
            Collections.sort(list,comp);
        }
        return list;
    }
}
