package hx.com.example.rule.param;

import hx.com.example.rule.common.param.BaseParam;
import hx.data.mybatis.annotation.EqualTo;
import hx.data.mybatis.param.PageParam;

public class RuleQueryParamDTO extends PageParam {

    @EqualTo(column = "rule_package")
    private String rulePackage;

    @EqualTo(column = "group")
    private String group;


    public String getRulePackage() {
        return rulePackage;
    }

    public void setRulePackage(String rulePackage) {
        this.rulePackage = rulePackage;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}