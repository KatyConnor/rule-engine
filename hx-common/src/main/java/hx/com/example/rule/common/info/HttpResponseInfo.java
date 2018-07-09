package hx.com.example.rule.common.info;

import java.io.Serializable;

/**
 * @Author mingliang
 * @Date 2018-01-29 17:08
 */
public class HttpResponseInfo implements Serializable {

    /** http 请求状态*/
    private String statusCode;
    /** 执行状态 */
    private String code;
    /** 返回调用提示信息 */
    private String message;
    /** 返回结果集， json字符串 */
    private HttpEntity entity;
    /** 错误码 */
    private String errorCode;
    /** 错误信息提示 */
    private String errorMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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

    public HttpEntity getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
