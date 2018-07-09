package hx.com.example.rule.form;

import hx.com.example.rule.common.form.BaseRequestForm;

/**
 * @Author mingliang
 * @Date 2018-01-31 14:37
 */
public class LoginForm extends BaseRequestForm {

    private String userName;
    private String passworld;
    private String systemCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
