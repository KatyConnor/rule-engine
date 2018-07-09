package hx.com.example.rule.impl;

import com.github.pagehelper.PageHelper;
import hx.com.example.rule.common.domain.MenuDomain;
import hx.com.example.rule.common.domain.MenuManageDomain;
import hx.com.example.rule.common.factory.MenuManageDomainFactory;
import hx.com.example.rule.common.utils.CollectionUtils;
import hx.com.example.rule.facade.RuleManageFacade;
import hx.com.example.rule.common.factory.KieContainerFactory;
import hx.com.example.rule.info.MenuInfo;
import hx.com.example.rule.order.KieContainerOrder;
import hx.com.example.rule.order.MenuQueryOrder;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2017-12-18 17:21
 */
@Service("ruleManageFacade")
public class RuleManageFacadeImpl implements RuleManageFacade {

    @Autowired
    private MenuManageDomainFactory menuManageDomainFactory;

    @Autowired
    private KieContainerFactory kieContainerFactory;

    @Override
    public KieContainer getKieContainer(KieContainerOrder order) {
        return kieContainerFactory.getKieContainer(order.getPackageName());
    }

    @Override
    public List<MenuInfo> getMenuInfos(MenuQueryOrder order) {
        MenuManageDomain menuManageDomain = menuManageDomainFactory.active(order.getSystemCode());
        List<MenuDomain> menuDomains = menuManageDomain.getMenuDomainList();
        List<MenuInfo> menuInfos = new ArrayList<>();
        Map<String,String> propertites = new HashMap<>();
        propertites.put("childMenuList","menuInfos");
        CollectionUtils.copy(menuDomains,menuInfos,MenuInfo.class,propertites);
        return menuInfos;
    }
}
