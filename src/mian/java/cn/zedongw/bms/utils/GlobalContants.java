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

    /**
     * 日期正则
     */
    public static final String DATE_REG = "^(((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8])))))))$";

}
