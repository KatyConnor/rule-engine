package hx.com.example.rule.common.info;

import java.io.Serializable;

/**
 *  返回处理结果集，JSON 字符串
 * @Author mingliang
 * @Date 2018-01-30 14:39
 */
public class HttpEntity implements Serializable{

    /** 结果处理是否成功 */
    private Boolean success;
    /** 状态码 */
    private String code;
    /** 处理结果 */
    private String data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
