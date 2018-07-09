package hx.com.example.rule.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author mingliang
 * @Date 2017-12-14 16:12
 */
@Getter
@Setter
public class RuleDomain extends Domain{

    /** 规则id */
    private Long ruleId;
    /** 规则编号 */
    private String ruleNumber;
    /** 是否执行规则 */
    private boolean excute;
    /** 规则包名，逻辑区分 */
    private String rulePackage;
    /** 规则名称 */
    private String ruleName;
    /** 当前规则是否循环执行，当前规则触发 */
    private boolean noLoop;
    /** 当前规则是否在外部规则触发循环执行  */
    private boolean lockOnActive;
    /** 规则条件 */
    private String when;
    /** 结果执行 */
    private String then;
    /** 规则过期时间 */
    private Date dateExpires;
    /** 规则生效时间 */
    private Date dateEffective;
    /** 规则定时执行时间 */
    private String duration;
    /** 执行优先级 */
    private Integer salience;
    /** 规则所属组 */
    private String group;
    /** 下一个规则的条件修改设置  */
    private String result;

    private String autoFocus;
    private String activationGroup;
    private String agendaGroup;
    private String ruleFlowGroup;
    private String entryPoint;
    private String dialect;
    private String enabled;
    private String attributes;
    private String extend;
    private String template;
    private String query;

}
