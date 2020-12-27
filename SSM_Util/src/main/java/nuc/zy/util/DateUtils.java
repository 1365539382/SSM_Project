package nuc.zy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转换成字符串
    public static String dateToString(Date date,String pattern){
        SimpleDateFormat df = new SimpleDateFormat(pattern) ;
        String format = df.format(date);
        return format ;
    }

    //字符串转换成日期
    public static Date stringToDate(String str,String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern) ;
        Date parse = df.parse(str);
        return parse ;
    }
}
