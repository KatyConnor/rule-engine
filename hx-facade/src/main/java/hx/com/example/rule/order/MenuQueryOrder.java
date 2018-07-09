package hx.com.example.rule.order;

import hx.com.example.rule.common.order.BaseOrder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author mingliang
 * @Date 2018-02-05 10:38
 */
public class MenuQueryOrder extends BaseOrder {

    @NotBlank
    private String systemCode;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
