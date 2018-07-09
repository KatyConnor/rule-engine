package hx.com.example.rule.common.form;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Author mingliang
 * @Date 2018-01-23 14:57
 */
public abstract class BaseRequestForm implements Serializable {

    @NotBlank
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
