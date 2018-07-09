package hx.com.example.rule.dto;

import hx.com.example.rule.common.dto.BaseDTO;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`rule_drl`")
public class RuleDrlDTO extends BaseDTO {
    /**
     * 主键ID
     */
    @Column(name = "`rule_drl_id`")
    private Long ruleDrlId;

    /**
     * 包名
     */
    @Column(name = "`package_name`")
    private String packageName;

    /**
     * 所属分组
     */
    @Column(name = "`group_id`")
    private String groupId;

    /**
     * 分组ID
     */
    @Column(name = "`artifact_id`")
    private String artifactId;

    /**
     * drl版本
     */
    @Column(name = "`rule_version`")
    private String ruleVersion;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 版本号
     */
    @Column(name = "`version`")
    private Long version;

    /**
     * 获取主键ID
     *
     * @return rule_drl_id - 主键ID
     */
    public Long getRuleDrlId() {
        return ruleDrlId;
    }

    /**
     * 设置主键ID
     *
     * @param ruleDrlId 主键ID
     */
    public void setRuleDrlId(Long ruleDrlId) {
        this.ruleDrlId = ruleDrlId;
    }

    /**
     * 获取包名
     *
     * @return package_name - 包名
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 设置包名
     *
     * @param packageName 包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    /**
     * 获取所属分组
     *
     * @return group_id - 所属分组
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置所属分组
     *
     * @param groupId 所属分组
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 获取分组ID
     *
     * @return artifact_id - 分组ID
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * 设置分组ID
     *
     * @param artifactId 分组ID
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId == null ? null : artifactId.trim();
    }

    /**
     * 获取drl版本
     *
     * @return rule_version - drl版本
     */
    public String getRuleVersion() {
        return ruleVersion;
    }

    /**
     * 设置drl版本
     *
     * @param ruleVersion drl版本
     */
    public void setRuleVersion(String ruleVersion) {
        this.ruleVersion = ruleVersion == null ? null : ruleVersion.trim();
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ruleDrlId=").append(ruleDrlId);
        sb.append(", packageName=").append(packageName);
        sb.append(", groupId=").append(groupId);
        sb.append(", artifactId=").append(artifactId);
        sb.append(", ruleVersion=").append(ruleVersion);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append("]");
        return sb.toString();
    }
}