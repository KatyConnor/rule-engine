package hx.com.example.rule.common.factory;

import hx.com.example.rule.common.domain.Domain;
import hx.com.example.rule.common.domain.MenuDomain;
import hx.com.example.rule.common.domain.MenuManageDomain;
import hx.com.example.rule.common.param.BaseParam;
import hx.com.example.rule.param.MenuQueryParamDTO;
import hx.data.mybatis.param.PageParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-02-05 10:56
 */
@Component
public class MenuManageDomainFactory extends AbstractDomainFactory<MenuManageDomain,MenuQueryParamDTO>{

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuManageDomainFactory.class);

    @Autowired
    private AutowiredDomainFactory autowiredDomainFactory;

    @Autowired
    private MenuDomainFactory menuDomainFactory;


    @Override
    public MenuManageDomain create() {
        return autowiredDomainFactory.create(MenuManageDomain.class);
    }

    @Override
    public MenuManageDomain active(String domainId) {
        MenuManageDomain menuManageDomain = autowiredDomainFactory.create(MenuManageDomain.class);
        MenuQueryParamDTO param = new MenuQueryParamDTO();
        param.setSystemCode(domainId);
        param.setPageNum(1);
        param.setPageSize(5);
        List<MenuDomain> menuDomains =  menuDomainFactory.activeList(param);
        menuManageDomain.initMenu(menuDomains);
        return menuManageDomain;
    }

    @Override
    public List<MenuManageDomain> activeList(MenuQueryParamDTO param) {
        return null;
    }

    @Override
    public Boolean store(MenuManageDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(MenuManageDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(List<MenuManageDomain> domains) {
        return null;
    }


}
