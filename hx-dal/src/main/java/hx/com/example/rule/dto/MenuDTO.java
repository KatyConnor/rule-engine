package hx.com.example.rule.dto;

import hx.com.example.rule.common.dto.BaseDTO;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`menu`")
public class MenuDTO extends BaseDTO {
    /**
     * 主键，菜单ID
     */
    @Id
    @Column(name = "`menu_id`")
    private Long menuId;

    /**
     * 父级菜单ID
     */
    @Column(name = "`parent_menu_id`")
    private Long parentMenuId;

    /**
     * 菜单编码
     */
    @Column(name = "`menu_code`")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "`menu_name`")
    private String menuName;

    /**
     * 页面跳转地址
     */
    @Column(name = "`url`")
    private String url;

    /**
     * 是否有效
     */
    @Column(name = "`effective`")
    private String effective;

    /**
     * 所属系统
     */
    @Column(name = "`system`")
    private String system;

    /**
     * 菜单循序
     */
    @Column(name = "`sort`")
    private String sort;

    /**
     * 菜单按钮图标
     */
    @Column(name = "`icon_url`")
    private String iconUrl;

    /**
     * 版本号
     */
    @Column(name = "`version`")
    private Long version;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 修时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 获取主键，菜单ID
     *
     * @return menu_id - 主键，菜单ID
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置主键，菜单ID
     *
     * @param menuId 主键，菜单ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取父级菜单ID
     *
     * @return parent_menu_id - 父级菜单ID
     */
    public Long getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 设置父级菜单ID
     *
     * @param parentMenuId 父级菜单ID
     */
    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    /**
     * 获取菜单编码
     *
     * @return menu_code - 菜单编码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编码
     *
     * @param menuCode 菜单编码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取页面跳转地址
     *
     * @return url - 页面跳转地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置页面跳转地址
     *
     * @param url 页面跳转地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取是否有效
     *
     * @return effective - 是否有效
     */
    public String getEffective() {
        return effective;
    }

    /**
     * 设置是否有效
     *
     * @param effective 是否有效
     */
    public void setEffective(String effective) {
        this.effective = effective == null ? null : effective.trim();
    }

    /**
     * 获取所属系统
     *
     * @return system - 所属系统
     */
    public String getSystem() {
        return system;
    }

    /**
     * 设置所属系统
     *
     * @param system 所属系统
     */
    public void setSystem(String system) {
        this.system = system == null ? null : system.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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
     * 获取修时间
     *
     * @return update_time - 修时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修时间
     *
     * @param updateTime 修时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", parentMenuId=").append(parentMenuId);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", menuName=").append(menuName);
        sb.append(", url=").append(url);
        sb.append(", effective=").append(effective);
        sb.append(", system=").append(system);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}