package hx.com.example.rule.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 系统菜单领域
 * @Author mingliang
 * @Date 2018-02-05 9:52
 */
@Getter
@Setter
public class MenuDomain extends Domain {

    /** 菜单ID */
    private Long menuId;
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

    /** 子菜单 */
    private List<MenuDomain> childMenuList;

    public static void main(String[] args) throws IllegalAccessException {
        MenuDomain domain = new MenuDomain();
        domain.setId("1");
        domain.setMenuName("FDSFSD");
//        Field[] fields = domain.getClass().getDeclaredFields();
        Field[] superields = domain.getClass().getSuperclass().getDeclaredFields();
        for (Field field : superields){
            field.setAccessible(true);
            if (field.getName().equals("id")){
                field.set(domain,"12");
            }
        }
        System.out.println(domain.getId());
    }

}
