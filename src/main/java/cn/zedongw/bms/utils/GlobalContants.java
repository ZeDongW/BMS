package cn.zedongw.bms.utils;

import java.text.SimpleDateFormat;

/**
 * @Author ZeDongW
 * @ClassName GlobalContants
 * @Description 全局变量
 * @Version 1.0
 * @date ：Created in 2019/4/20 0020 7:26
 * @modified By：
 */

public class GlobalContants {

    /**
     * 定义日期字符串格式为yyyy-MM-dd
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 用户名正则
     */
    public static final String NAME_REG = "^[a-zA-Z0-9]{2,16}$";

    /**
     * 密码正则
     */
    public static final String PASS_REG = "^(?![0-9\\W]+$)[a-zA-Z0-9\\W]{6,16}$";
}
