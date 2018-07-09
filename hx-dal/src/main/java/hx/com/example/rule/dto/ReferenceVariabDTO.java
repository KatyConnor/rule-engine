package hx.com.example.rule.dto;

import hx.com.example.rule.common.dto.BaseDTO;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`reference_variab`")
public class ReferenceVariabDTO extends BaseDTO {
    /**
     * 主键ID
     */
    @Column(name = "`variab_id`")
    private Long variabId;

    /**
     * 引用变量包名
     */
    @Column(name = "`variab_name`")
    private String variabName;

    /**
     * 外键, package 唯一
     */
    @Column(name = "`package_name`")
    private String packageName;

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
     * 获取主键ID
     *
     * @return variab_id - 主键ID
     */
    public Long getVariabId() {
        return variabId;
    }

    /**
     * 设置主键ID
     *
     * @param variabId 主键ID
     */
    public void setVariabId(Long variabId) {
        this.variabId = variabId;
    }

    /**
     * 获取引用变量包名
     *
     * @return variab_name - 引用变量包名
     */
    public String getVariabName() {
        return variabName;
    }

    /**
     * 设置引用变量包名
     *
     * @param variabName 引用变量包名
     */
    public void setVariabName(String variabName) {
        this.variabName = variabName == null ? null : variabName.trim();
    }

    /**
     * 获取外键, package 唯一
     *
     * @return package_name - 外键, package 唯一
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 设置外键, package 唯一
     *
     * @param packageName 外键, package 唯一
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", variabId=").append(variabId);
        sb.append(", variabName=").append(variabName);
        sb.append(", packageName=").append(packageName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append("]");
        return sb.toString();
    }
}