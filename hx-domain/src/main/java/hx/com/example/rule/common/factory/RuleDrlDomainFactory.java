package hx.com.example.rule.common.factory;

import hx.com.example.rule.dao.RuleDrlMapperDAO;
import hx.com.example.rule.common.domain.RuleDrlDomain;
import hx.com.example.rule.dto.RuleDrlDTO;
import hx.com.example.rule.param.RuleDrlQueryParamDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-12-14 14:28
 */
@Component
public class RuleDrlDomainFactory extends AbstractDomainFactory<RuleDrlDomain,RuleDrlQueryParamDTO> {

    @Autowired
    private AutowiredDomainFactory autowiredDomainFactory;

    @Autowired
    private ReferenceVariabDomainFactory referenceVariabDomainFactory;

//    @Autowired
    private RuleDrlMapperDAO ruleDrlMapperDAO;

    @Override
    public RuleDrlDomain create() {
        RuleDrlDomain domain = autowiredDomainFactory.create(RuleDrlDomain.class);
        return domain;
    }

    @Override
    public RuleDrlDomain active(String domainId) {
        return null;
    }

    @Override
    public List<RuleDrlDomain> activeList(RuleDrlQueryParamDTO paramDTO) {
        List<RuleDrlDTO> ruleDrlDTOS = null;
        List<RuleDrlDomain> ruleDrlDomains = new ArrayList<>();
        if(paramDTO == null){
//            ruleDrlDTOS = ruleDrlMapperDAO.selectAll();
        }else {
//            ruleDrlDTOS = ruleDrlMapperDAO.findDynamicConditional(paramDTO);
        }

        if (CollectionUtils.isEmpty(ruleDrlDTOS)){
            return ruleDrlDomains;
        }
        for (RuleDrlDTO ruleDrlDTO : ruleDrlDTOS){
            RuleDrlDomain domain = autowiredDomainFactory.create(RuleDrlDomain.class);
            BeanUtils.copyProperties(ruleDrlDTO,domain);
            ruleDrlDomains.add(domain);
        }
        return ruleDrlDomains;
    }

    @Override
    public Boolean store(RuleDrlDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(RuleDrlDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(List<RuleDrlDomain> domain) {
        return null;
    }
}
