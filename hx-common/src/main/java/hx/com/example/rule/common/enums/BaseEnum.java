//package hx.com.example.rule.common.enums;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @Author mingliang
// * @Date 2018-01-29 17:48
// */
//public abstract class BaseEnum<ENUM extends Enum<?>, KEYTYPE> {
//
//    private KEYTYPE code;
//    private String message;
//
//    public KEYTYPE getCode() {
//        return code;
//    }
//
//    public void setCode(KEYTYPE code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    /**
//     *  根据code获取枚举描述
//     * @param code
//     * @return
//     */
//    public static String getMsgByCode(String code){
//        List<BaseEnum> _enumList = getAll();
//        for (BaseEnum _enum : _enumList){
//            if (_enum.getCode().equals(code)){
//                return _enum.getMessage();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 根据code获取枚举类
//     * @param code
//     * @return
//     */
//    public static BaseEnum getByCode(String code){
//        List<BaseEnum> errorcedeEnumList = getAll();
//        for (BaseEnum _enum : errorcedeEnumList){
//            if (_enum.getCode().equals(code)){
//                return _enum;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 根据code获取枚举类
//     * @param message
//     * @return
//     */
//    public static BaseEnum getByMessage(String message){
//        List<BaseEnum> errorcedeEnumList = getAll();
//        for (BaseEnum _enum : errorcedeEnumList){
//            if (_enum.getMessage().equals(message)){
//                return _enum;
//            }
//        }
//        return null;
//    }
//
//
//    public static <KEYTYPE> KEYTYPE getCodeByMessage(String message){
//        List<BaseEnum> errorcedeEnumList = getAll();
//        for (BaseEnum _enum : errorcedeEnumList){
//            if (_enum.getMessage().equals(message)){
//                return (KEYTYPE) _enum.getCode();
//            }
//        }
//        return null;
//    }
//    /**
//     * 获取所有枚举
//     * @return
//     */
//    public static List<BaseEnum> getAll(){
//        return Arrays.asList(values());
//    }
//
//    public abstract List<BaseEnum> values();
//}
