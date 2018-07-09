package hx.com.example.rule.dto;

import hx.com.example.rule.common.dto.BaseDTO;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`rule`")
public class RuleDTO extends BaseDTO {
    /**
     * 主键，规则ID
     */
    @Column(name = "`rule_id`")
    private Long ruleId;

    /**
     * 规则名称
     */
    @Column(name = "`rule_name`")
    private String ruleName;

    /**
     * 包名，对应规则文件表的package
     */
    @Column(name = "`rule_package`")
    private String rulePackage;

    /**
     * 前规则是否循环执行，当前规则触发
     */
    @Column(name = "`no_loop`")
    private String noLoop;

    /**
     * 当前规则是否在外部规则触发循环执行
     */
    @Column(name = "`lock_on_active`")
    private String lockOnActive;

    /**
     * 规则过期时间
     */
    @Column(name = "`date_expires`")
    private Date dateExpires;

    /**
     * 规则生效时间
     */
    @Column(name = "`date_effective`")
    private Date dateEffective;

    /**
     * 规则定时执行时间,满足corn时间表达式
     */
    @Column(name = "`duration`")
    private String duration;

    /**
     * 执行优先级
     */
    @Column(name = "`salience`")
    private Integer salience;

    /**
     * 规则条件
     */
    @Column(name = "`when`")
    private String when;

    /**
     * 结果执行
     */
    @Column(name = "`then`")
    private String then;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 版本号
     */
    @Column(name = "`version`")
    private Long version;

    /**
     * 规则所属组
     */
    @Column(name = "`group`")
    private String group;

    /**
     * 规则执行优先级
     */
    @Column(name = "`order`")
    private String order;

    /**
     * 规则执行结果
     */
    @Column(name = "`result`")
    private String result;

    /**
     * 获取主键，规则ID
     *
     * @return rule_id - 主键，规则ID
     */
    public Long getRuleId() {
        return ruleId;
    }

    /**
     * 设置主键，规则ID
     *
     * @param ruleId 主键，规则ID
     */
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 获取规则名称
     *
     * @return rule_name - 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置规则名称
     *
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    /**
     * 获取包名，对应规则文件表的package
     *
     * @return rule_package - 包名，对应规则文件表的package
     */
    public String getRulePackage() {
        return rulePackage;
    }

    /**
     * 设置包名，对应规则文件表的package
     *
     * @param rulePackage 包名，对应规则文件表的package
     */
    public void setRulePackage(String rulePackage) {
        this.rulePackage = rulePackage == null ? null : rulePackage.trim();
    }

    /**
     * 获取前规则是否循环执行，当前规则触发
     *
     * @return no_loop - 前规则是否循环执行，当前规则触发
     */
    public String getNoLoop() {
        return noLoop;
    }

    /**
     * 设置前规则是否循环执行，当前规则触发
     *
     * @param noLoop 前规则是否循环执行，当前规则触发
     */
    public void setNoLoop(String noLoop) {
        this.noLoop = noLoop == null ? null : noLoop.trim();
    }

    /**
     * 获取当前规则是否在外部规则触发循环执行
     *
     * @return lock_on_active - 当前规则是否在外部规则触发循环执行
     */
    public String getLockOnActive() {
        return lockOnActive;
    }

    /**
     * 设置当前规则是否在外部规则触发循环执行
     *
     * @param lockOnActive 当前规则是否在外部规则触发循环执行
     */
    public void setLockOnActive(String lockOnActive) {
        this.lockOnActive = lockOnActive == null ? null : lockOnActive.trim();
    }

    /**
     * 获取规则过期时间
     *
     * @return date_expires - 规则过期时间
     */
    public Date getDateExpires() {
        return dateExpires;
    }

    /**
     * 设置规则过期时间
     *
     * @param dateExpires 规则过期时间
     */
    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    /**
     * 获取规则生效时间
     *
     * @return date_effective - 规则生效时间
     */
    public Date getDateEffective() {
        return dateEffective;
    }

    /**
     * 设置规则生效时间
     *
     * @param dateEffective 规则生效时间
     */
    public void setDateEffective(Date dateEffective) {
        this.dateEffective = dateEffective;
    }

    /**
     * 获取规则定时执行时间,满足corn时间表达式
     *
     * @return duration - 规则定时执行时间,满足corn时间表达式
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 设置规则定时执行时间,满足corn时间表达式
     *
     * @param duration 规则定时执行时间,满足corn时间表达式
     */
    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    /**
     * 获取执行优先级
     *
     * @return salience - 执行优先级
     */
    public Integer getSalience() {
        return salience;
    }

    /**
     * 设置执行优先级
     *
     * @param salience 执行优先级
     */
    public void setSalience(Integer salience) {
        this.salience = salience;
    }

    /**
     * 获取规则条件
     *
     * @return when - 规则条件
     */
    public String getWhen() {
        return when;
    }

    /**
     * 设置规则条件
     *
     * @param when 规则条件
     */
    public void setWhen(String when) {
        this.when = when == null ? null : when.trim();
    }

    /**
     * 获取结果执行
     *
     * @return then - 结果执行
     */
    public String getThen() {
        return then;
    }

    /**
     * 设置结果执行
     *
     * @param then 结果执行
     */
    public void setThen(String then) {
        this.then = then == null ? null : then.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ruleId=").append(ruleId);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", rulePackage=").append(rulePackage);
        sb.append(", noLoop=").append(noLoop);
        sb.append(", lockOnActive=").append(lockOnActive);
        sb.append(", dateExpires=").append(dateExpires);
        sb.append(", dateEffective=").append(dateEffective);
        sb.append(", duration=").append(duration);
        sb.append(", salience=").append(salience);
        sb.append(", when=").append(when);
        sb.append(", then=").append(then);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append("]");
        return sb.toString();
    }
}