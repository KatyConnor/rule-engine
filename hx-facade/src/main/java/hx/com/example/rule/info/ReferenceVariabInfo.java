package hx.com.example.rule.info;

import hx.com.example.rule.common.info.BaseInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author mingliang
 * @Date 2018-01-09 14:11
 */
@Getter
@Setter
public class ReferenceVariabInfo extends BaseInfo {

    private Long variabId;
    private String variabName;
    private String packageName;
}
