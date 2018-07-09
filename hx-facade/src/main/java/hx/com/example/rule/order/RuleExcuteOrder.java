package hx.com.example.rule.order;

import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.enums.HttpTypeEnum;
import hx.com.example.rule.common.order.BaseOrder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-12-28 14:43
 */
@Getter
@Setter
public class RuleExcuteOrder extends BaseOrder{

    @NotBlank
    private String packageName;
    @NotNull
    private JSONObject data;
    @NonNull
    private HttpTypeEnum requestMethod;
    @NotBlank
    private String url;
    @NotBlank
    private String group;
    @NotNull
    private Boolean asyn;
}
