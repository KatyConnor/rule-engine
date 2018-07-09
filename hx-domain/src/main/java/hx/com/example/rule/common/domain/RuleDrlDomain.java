package hx.com.example.rule.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * 规则配置管理领域
 * @Author mingliang
 * @Date 2017-12-14 14:14
 */
@Getter
@Setter
public class RuleDrlDomain extends Domain {

    /** 规则包名，逻辑区分 */
    private String packageName;
    /** 规则需要使用的外部变量 */
    private List<String> importList;
    /** 全局变量 */
    private List<String> globals;
    /** 规则自定义函数 */
    private List<String> function;
    /**  */
    private List<String> queries;
    /** 组 */
    private String groupId;
    /** 分组id */
    private String artifactId;
    /** 版本号 */
    private String ruleVersion;

    /** 点钱规则包下面存在的规则*/
    private List<RuleDomain> ruleDomains;

    public boolean execute(Object object){
        return false;
    }
}
