package hx.com.example.rule.common.velocity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义模板函数
 * @Author mingliang
 * @Date 2018-01-31 11:09
 */
public class VelocityUtils {

    /**
     * Date 类型时间格式化成字符串
     * @param date  时间
     * @param formatStr 时间格式
     * @return 返回格式化后的 date
     */
    public static String formatDate(Date date,String formatStr){
        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        return sf.format(date);
    }
}
