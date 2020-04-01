package cn.zedongw.bms.dateconvert;

import org.apache.struts2.util.StrutsTypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName MyDateConvert
 * @Description: 自定义日期格式转换器
 * @Author ZeDongW
 * @Date 2020/3/9 0009 9:13
 * @Version V1.0
 **/
public class MyDateConvert extends StrutsTypeConverter {

    /**
     * 日期类型转换集合
     */
    SimpleDateFormat[] sdfs ={new SimpleDateFormat("yyyy-MM-dd"),
                              new SimpleDateFormat("yyyy/MM/dd"),
                              new SimpleDateFormat("yyyyMMdd"),
                              new SimpleDateFormat("yyyy年MM月dd日")};

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values == null || values.length == 0) {
            return null;
        }

        if (Date.class != toClass){
            return null;
        }

        for (int i = 0; i < sdfs.length; i++) {
            try {
                return sdfs[i].parse(values[0]);
            } catch (ParseException e) {
                continue;
            }
        }
        return null;
    }

    @Override
    public String convertToString(Map context, Object o) {
        return null;
    }
}
