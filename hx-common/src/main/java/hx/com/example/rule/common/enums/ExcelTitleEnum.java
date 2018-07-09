package hx.com.example.rule.common.enums;

import java.util.Arrays;
import java.util.List;

/**
 *  EXCEL 导入表头枚举
 * @Author mingliang
 * @Date 2017-12-27 16:12
 */
public enum ExcelTitleEnum {

    LOAN_NUMBER("loanNumber","贷款合同号","String"),
    USER_NAME("userName","姓名","String"),
    TRANS_CONTRACT_SIGN_TIME("transferContractSignTime","转移合同签署时间","Date"),
    TRANS_CONTRACT_EFFECT_TIME("transferContractEffectTime","转移合同生效时间","Date"),
    CERT_NO("certNo","身份证号","String"),
    SIGN_LOAN_TIME("sigLoanTime","贷款合同签署时间","Date"),
    TEL_NO("telNo","电话号码","String"),
    LOAN_CAPITAL("loanCapital","放款初始本金","double"),
    LENDING_TIME("lendingTime","放款时间","Date"),
    PAY_PRINCIPAL("payPrincipal","已付本金","double"),
    PAID_INTEREST("paidInterest","已付利息","double"),
    PAID_FINE("paidFine","已付罚息","double"),
    UNPAID_PRINCIPAL("unpaidPrincipal","未付本金","double"),
    UNPAID_INTEREST("unpaidInterest","未付利息","double"),
    TRANSFER_AMOUNT("transferAmount","债权转让金额","double");

    private String code;
    private String message;
    private String type;

    ExcelTitleEnum(String code, String message, String type) {
        this.code = code;
        this.message = message;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *  根据code获取枚举描述
     * @param code
     * @return
     */
    public static String getMsgByCode(String code){
        List<ExcelTitleEnum> _enumList = getAll();
        for (ExcelTitleEnum _enum : _enumList){
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
    public static ExcelTitleEnum getByCode(String code){
        List<ExcelTitleEnum> errorcedeEnumList = getAll();
        for (ExcelTitleEnum _enum : errorcedeEnumList){
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
    public static ExcelTitleEnum getByMessage(String message){
        List<ExcelTitleEnum> errorcedeEnumList = getAll();
        for (ExcelTitleEnum _enum : errorcedeEnumList){
            if (_enum.getMessage().equals(message)){
                return _enum;
            }
        }
        return null;
    }


    public static String getCodeByMessage(String message){
        List<ExcelTitleEnum> errorcedeEnumList = getAll();
        for (ExcelTitleEnum _enum : errorcedeEnumList){
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
    public static List<ExcelTitleEnum> getAll(){
        return Arrays.asList(ExcelTitleEnum.values());
    }

}
