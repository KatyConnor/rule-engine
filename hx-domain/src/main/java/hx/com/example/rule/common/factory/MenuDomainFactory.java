package hx.com.example.rule.common.factory;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hx.com.example.rule.common.domain.MenuDomain;
import hx.com.example.rule.common.utils.CollectionUtils;
import hx.com.example.rule.dao.MenuMapperDAO;
import hx.com.example.rule.dto.MenuDTO;
import hx.com.example.rule.param.MenuQueryParamDTO;
import hx.data.mybatis.utils.PageResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-02-05 10:28
 */
@Component
public class MenuDomainFactory extends AbstractDomainFactory<MenuDomain,MenuQueryParamDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDomainFactory.class);

    @Autowired
    private AutowiredDomainFactory autowiredDomainFactory;

    @Autowired
    private MenuMapperDAO menuMapperDAO;

    @Override
    public MenuDomain create() {
        return autowiredDomainFactory.create(MenuDomain.class);
    }

    @Override
    public MenuDomain active(String domainId) {
        return null;
    }

    @Override
    public List<MenuDomain> activeList(MenuQueryParamDTO param) {
//        System.out.printf("------ = "+JSONArray.toJSONString( menuMapperDAO.selectAll()));
//        List<MenuDTO> menuDTOList = menuMapperDAO.findDynamicConditionalByPage(param);
//        List<MenuDTO> menuDTOLists = menuMapperDAO.selectByCondition(param);
//        System.out.printf("------ = "+JSONArray.toJSONString(menuDTOLists));
//        List<MenuDomain> menuDomains = new ArrayList<>(menuDTOList.size());
//        CollectionUtils.copy(menuDTOList,menuDomains,MenuDomain.class);
//        return menuDomains;
        return null;
    }

    @Override
    public Boolean store(MenuDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(MenuDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(List<MenuDomain> domains) {
        return null;
    }
}
