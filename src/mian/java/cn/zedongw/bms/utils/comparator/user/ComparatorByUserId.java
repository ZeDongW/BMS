package cn.zedongw.bms.utils.comparator.user;

import cn.zedongw.bms.entity.Users;

import java.util.Comparator;

/**
 * @Author ZeDongW
 * @ClassName ComparatorByUserId
 * @Description 根据用户编号排序
 * @Version 1.0
 * @date ：Created in 2019/5/13 0013 7:06
 * @modified By：
 */

public class ComparatorByUserId implements Comparator<Users> {
    @Override
    public int compare(Users o1, Users o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
