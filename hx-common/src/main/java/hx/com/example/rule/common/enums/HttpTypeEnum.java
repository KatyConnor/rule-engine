package hx.com.example.rule.common.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-12-28 14:47
 */
public enum HttpTypeEnum {

    GET("GET","GET请求"),
    POST("POST","POST请求");

    private String code;
    private String message;

    HttpTypeEnum(String code, String message) {
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
        List<HttpTypeEnum> _enumList = getAll();
        for (HttpTypeEnum _enum : _enumList){
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
    public static HttpTypeEnum getByCode(String code){
        List<HttpTypeEnum> errorcedeEnumList = getAll();
        for (HttpTypeEnum _enum : errorcedeEnumList){
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
    public static HttpTypeEnum getByMessage(String message){
        List<HttpTypeEnum> errorcedeEnumList = getAll();
        for (HttpTypeEnum _enum : errorcedeEnumList){
            if (_enum.getMessage().equals(message)){
                return _enum;
            }
        }
        return null;
    }


    public static String getCodeByMessage(String message){
        List<HttpTypeEnum> errorcedeEnumList = getAll();
        for (HttpTypeEnum _enum : errorcedeEnumList){
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
    public static List<HttpTypeEnum> getAll(){
        return Arrays.asList(HttpTypeEnum.values());
    }
}
