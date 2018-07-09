package hx.com.example.rule.common.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author mingliang
 * @Date 2017-12-20 15:58
 */
@Getter
@Setter
public class ReferenceVariabDomain extends Domain{

    private Long variabId;
    private String variabName;
    private String packageName;

}
