package hx.com.example.rule.common.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Http 请求处理状态
 * @Author mingliang
 * @Date 2018-01-29 17:44
 */
public enum HttpCodeRnum {

    SUCCESS("200","请求成功"),
    CONNECT_SUCCESS("201","调用成功"),
    FAILED("5001","处理失败");

    private String code;
    private String message;

    HttpCodeRnum(String code, String message) {
        this.code = code;
        this.message = message;
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

    /**
     *  根据code获取枚举描述
     * @param code
     * @return
     */
    public static String getMsgByCode(String code){
        List<HttpCodeRnum> _enumList = getAll();
        for (HttpCodeRnum _enum : _enumList){
            if (_enum.getCode().equals(code) ){
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
    public static HttpCodeRnum getByCode(String code){
        List<HttpCodeRnum> errorcedeEnumList = getAll();
        for (HttpCodeRnum _enum : errorcedeEnumList){
            if (_enum.getCode().equals(code) ){
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
    public static HttpCodeRnum getByMessage(String message){
        List<HttpCodeRnum> errorcedeEnumList = getAll();
        for (HttpCodeRnum _enum : errorcedeEnumList){
            if (_enum.getMessage().equals(message)){
                return _enum;
            }
        }
        return null;
    }


    public static String getCodeByMessage(String message){
        List<HttpCodeRnum> errorcedeEnumList = getAll();
        for (HttpCodeRnum _enum : errorcedeEnumList){
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
    public static List<HttpCodeRnum> getAll(){
        return Arrays.asList(HttpCodeRnum.values());
    }
}
