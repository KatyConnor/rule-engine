package hx.com.example.rule.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author mingliang
 * @Date 2017-08-30 17:30
 */

public class DateUtils {

    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static String DATE_FORMAT_NUMBER = "yyyyMMddHHmmss";

    private final static String SPECIFIC_DATE_FORMAT = "yyyy-MM-dd";

    private final static String EVERY_MINUTE_FORMAT = "HH:mm:ss";

    public static final String BACK_SLASH = "\\\\";

    private static SimpleDateFormat simpleDateFormat;

    public static String dateFormat(Date date){
        simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String dateFormatNumber(Date date){
        simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_NUMBER);
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        String ss = "/fds\\fdsffa/fdsfsd/dsd/";
        System.out.println(ss.replaceAll(BACK_SLASH,"/"));
//        System.out.println(ss+"  \\\\");
        System.out.println(ss.indexOf("/",1));
        System.out.println(ss.equalsIgnoreCase("/"));
        System.out.println(ss.lastIndexOf("/")+"----"+(ss.length()-1));
    }
}
