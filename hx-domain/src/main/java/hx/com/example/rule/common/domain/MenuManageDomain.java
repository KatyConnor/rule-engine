package hx.com.example.rule.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-02-05 10:52
 */
@Getter
@Setter
public class MenuManageDomain extends Domain {

    private List<MenuDomain> menuDomainList;

    /**
     *  初始化菜单，按照ID分组
     */
    public void initMenu(List<MenuDomain> domains){
        List<MenuDomain> domainList = new ArrayList<>(domains.size());
        // 先找到第一级菜单
        for (MenuDomain domain : domains){
            if (domain.getParentMenuId() == 0){
                domainList.add(domain);
            }
        }

        for (MenuDomain domain : domainList){
            sortMenu(domain,domains);
        }

        this.menuDomainList = domainList;
    }

    /**
     * 一级一级寻找下一级的子菜单
     * @param parentDomain
     * @param domains
     */
    private void sortMenu(MenuDomain parentDomain,List<MenuDomain> domains){
        List<MenuDomain> childMenuDomainList = parentDomain.getChildMenuList();
        for (MenuDomain childDomain : domains){
            if (parentDomain.getMenuId().equals(childDomain.getParentMenuId())){
                sortMenu(childDomain,domains);
                if (CollectionUtils.isEmpty(childMenuDomainList)){
                    childMenuDomainList = new ArrayList<>();
                }
                childMenuDomainList.add(childDomain);
            }
        }
        parentDomain.setChildMenuList(childMenuDomainList);
    }
}
