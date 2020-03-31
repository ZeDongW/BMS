package cn.zedongw.bms.utils;

import java.text.ParseException;
import java.util.Date;

/**
 * @Author ZeDongW
 * @ClassName MyDateUtils
 * @Description 日期工具类，将字符串日期按照指定格式相互转化
 * @Version 1.0
 * @date ：Created in 2019/4/19 0019 22:42
 * @modified By：
 */

public class MyDateUtils {

    /**
     * @Author: ZeDongW
     * @Description: 将日期按标准转换成字符串
     * @Date: 2019/4/19 0019 22:37
     * @Param: [date]
     * @return: java.lang.String
     */
    public static String dateToStr(Date date){
        return GlobalContants.SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * @Author: ZeDongW
     * @Description: 将字符串转换成指定格式日期
     * @Date: 2019/4/19 0019 22:41
     * @Param: [str]
     * @return: java.util.Date
     */
    public static Date strToDate(String str) throws ParseException {
        return GlobalContants.SIMPLE_DATE_FORMAT.parse(str);
    }
}
