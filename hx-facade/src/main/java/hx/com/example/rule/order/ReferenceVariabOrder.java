package hx.com.example.rule.order;

import hx.com.example.rule.common.order.BaseOrder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author mingliang
 * @Date 2018-01-09 14:14
 */
public class ReferenceVariabOrder extends BaseOrder{

    @NotBlank
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
