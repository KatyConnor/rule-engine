package hx.com.example.rule.common.factory;

//import hx.com.example.rule.dao.RuleMapperDAO;
import hx.com.example.rule.common.domain.RuleDomain;
import hx.com.example.rule.dao.RuleMapperDAO;
import hx.com.example.rule.dto.RuleDTO;
import hx.com.example.rule.param.RuleQueryParamDTO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-12-20 10:31
 */
@Component
public class RuleDomainFactory extends AbstractDomainFactory<RuleDomain,RuleQueryParamDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleDomainFactory.class);

//    @Autowired
    private RuleMapperDAO ruleMapperDAO;

    @Autowired
    private AutowiredDomainFactory autowiredDomainFactory;

    @Override
    public RuleDomain create() {
        return null;
    }

    @Override
    public RuleDomain active(String domainId) {
        return null;
    }

    @Override
    public List<RuleDomain> activeList(RuleQueryParamDTO paramDTO) {
        List<RuleDTO> ruleDTOS = null;
        List<RuleDomain> ruleDomains = new ArrayList<>();
        if (paramDTO == null){
//            ruleDTOS = ruleMapperDAO.selectAll();
        }else {
//            ruleDTOS = ruleMapperDAO.findDynamicConditional(paramDTO);
        }
        if (CollectionUtils.isEmpty(ruleDTOS)){
            return ruleDomains;
        }
        for (RuleDTO ruleDTO : ruleDTOS){
            RuleDomain domain = autowiredDomainFactory.create(RuleDomain.class);
            BeanUtils.copyProperties(ruleDTO,domain);
            ruleDomains.add(domain);
        }
        return ruleDomains;
    }

    @Override
    public Boolean store(RuleDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(RuleDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(List<RuleDomain> domains) {
        return null;
    }
}
