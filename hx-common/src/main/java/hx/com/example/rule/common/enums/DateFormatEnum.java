package hx.com.example.rule.common.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author mingliang
 * @Date 2018-01-05 17:05
 */
public enum DateFormatEnum {

    DATE_1("DATE_1","yyyy-MM-dd HH:mm:ss","","\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}"),
    DATE_2("DATE_2","yyyy-MM-dd"," 00:00:00","\\d{4}-\\d{1,2}-\\d{1,2}"),
    DATE_3("DATE_3","yyyyMMddHHmmss","","\\d{4}\\d{1,2}\\d{1,2}\\s\\d{2}\\d{2}\\d{2}"),
    DATE_4("DATE_4","yyyy/MM/dd"," 00:00:00","\\d{4}/\\d{1,2}/\\d{1,2}"),
    DATE_5("DATE_5","yyyy/MM/dd HH:mm:ss","","\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}"),
    DATE_6("DATE_6","yyyy年MM月dd日 HH:mm:ss","","\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{2}:\\d{2}:\\d{2}"),
    DATE_7("DATE_7","yyyy年MM月dd日 HH时mm分ss秒","","\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{2}时\\d{2}分\\d{2}秒"),
    DATE_8("DATE_8","yyyy年MM月dd日"," 00:00:00","\\d{4}年\\d{1,2}月\\d{1,2}日"),
    DATE_9("DATE_9","yyyy-MM-dd HH:mm",":00","\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}"),
    DATE_10("DATE_10","yyyy-MM-dd HH",":00:00","\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}"),
    DATE_11("DATE_11","yyyy/MM/dd HH:mm",":00","\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{2}:\\d{2}"),
    DATE_12("DATE_12","yyyy/MM/dd HH",":00:00","\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{2}"),
    DATE_13("DATE_13","yyyyMMddHH","0000","\\d{4}\\d{1,2}\\d{1,2}\\s\\d{2}"),
    DATE_14("DATE_14","yyyyMMddHHmm","00","\\d{4}\\d{1,2}\\d{1,2}\\s\\d{2}\\d{2}"),
    DATE_15("DATE_14","yyyy年MM月dd日 HH时mm分","00秒","\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{2}时\\d{2}分"),
    DATE_16("DATE_14","yyyy年MM月dd日 HH时","00分:00秒","\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{2}时");

    private String code;
    private String message;
    private String endTime;
    private String regStr;

    DateFormatEnum(String code, String message, String endTime, String regStr) {
        this.code = code;
        this.message = message;
        this.endTime = endTime;
        this.regStr = regStr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRegStr() {
        return regStr;
    }

    public void setRegStr(String regStr) {
        this.regStr = regStr;
    }

    /**
     *  根据code获取枚举描述
     * @param code
     * @return
     */
    public static String getMsgByCode(String code){
        List<DateFormatEnum> _enumList = getAll();
        for (DateFormatEnum _enum : _enumList){
            if (_enum.getCode().equals(code)){
                return _enum.getMessage();
            }
        }
        return null;
    }

    /**
     * 根据code获取枚举类
     * @param code
     * @return
     */
    public static DateFormatEnum getByCode(String code){
        List<DateFormatEnum> errorcedeEnumList = getAll();
        for (DateFormatEnum _enum : errorcedeEnumList){
            if (_enum.getCode().equals(code)){
                return _enum;
            }
        }
        return null;
    }

    /**
     * 根据code获取枚举类
     * @param message
     * @return
     */
    public static DateFormatEnum getByMessage(String message){
        List<DateFormatEnum> errorcedeEnumList = getAll();
        for (DateFormatEnum _enum : errorcedeEnumList){
            if (_enum.getMessage().equals(message)){
                return _enum;
            }
        }
        return null;
    }


    public static String getCodeByMessage(String message){
        List<DateFormatEnum> errorcedeEnumList = getAll();
        for (DateFormatEnum _enum : errorcedeEnumList){
            if (_enum.getMessage().equals(message)){
                return _enum.getCode();
            }
        }
        return null;
    }
    /**
     * 获取所有枚举
     * @return
     */
    public static List<DateFormatEnum> getAll(){
        return Arrays.asList(DateFormatEnum.values());
    }

    /**
     * 获取格式为 Y-M-D H:m:s 的时间
     * @param strDate
     * @return
     */
    public String getCompletionTime(String strDate){
        List<DateFormatEnum> list = getAll();
        boolean next = false;
        for (DateFormatEnum _enum : list){
            if (!next){
                next = isCompletionTime(strDate,_enum.getRegStr());
            }else {
                return strDate+_enum.getEndTime();
            }
        }
        return null;
    }

    /**
     * 获取正则串
     * @return
     */
    public static String getRegString(){
       StringBuilder sb = new StringBuilder();
       List<DateFormatEnum> enumList = getAll();
       for (DateFormatEnum _enum : enumList){
           sb.append("(").append(_enum.getRegStr()).append(")|");
       }
       sb.substring(0,sb.length()-1);
        return sb.toString();
    }

    public static String getTimeByDateStr(String strDate){
        List<DateFormatEnum> list = getAll();
        for (DateFormatEnum _enum : list){
            Pattern pattern = Pattern.compile(_enum.getRegStr());
            Matcher m = pattern.matcher(strDate);
            if (m.matches()) {
                return strDate+_enum.getEndTime();
            } else {
                continue;
            }
        }
        return null;
    }

    /**
     * 字符串是否为正确的时间格式
     * @param strDate
     * @param regStr
     * @return
     */
    private boolean isCompletionTime(String strDate, String regStr){
        Pattern pattern = Pattern.compile(regStr);
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取时间不是 Y-M-D H:m:s 的时间集合
     * @return
     */
    private List<DateFormatEnum> getAllDefective(){
        List<DateFormatEnum> list = getAll();
        List<DateFormatEnum> enumList = new ArrayList<>();
        for (DateFormatEnum _enum : list){
            if (!_enum.getEndTime().equals("")){
                enumList.add(_enum);
            }
        }
        return enumList;
    }

}
