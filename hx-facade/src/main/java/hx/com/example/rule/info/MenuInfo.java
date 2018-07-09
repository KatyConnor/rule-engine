package hx.com.example.rule.info;

import hx.com.example.rule.common.info.BaseInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-02-05 10:36
 */
@Getter
@Setter
public class MenuInfo extends BaseInfo {

    /** 父级菜单ID */
    private Long parentMenuId;

    /** 菜单编码  */
    private String menuCode;

    /** 菜单名称  */
    private String menuName;

    /** 页面跳转地址  */
    private String url;

    /** 是否有效 */
    private String effective;

    /** 所属系统 */
    private String system;

    /** 菜单循序 */
    private String sort;

    /** 菜单按钮图标 */
    private String iconUrl;

    private List<MenuInfo> menuInfos;
}
