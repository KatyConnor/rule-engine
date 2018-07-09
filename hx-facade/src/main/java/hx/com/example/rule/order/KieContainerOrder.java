package hx.com.example.rule.order;

import hx.com.example.rule.common.order.BaseOrder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author mingliang
 * @Date 2018-01-23 17:19
 */
@Getter
@Setter
public class KieContainerOrder extends BaseOrder {

    @NotBlank
    private String packageName;
}
