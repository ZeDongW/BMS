package cn.zedongw.bms.utils;

import java.util.UUID;

/**
 * @Author ZeDongW
 * @ClassName IDUtils
 * @Description 获取随机ID
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:49
 * @modified By：
 */

public class IDUtils {
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
