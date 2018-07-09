package hx.com.example.rule.common.factory;

import hx.com.example.rule.dao.ReferenceVariabMapperDAO;
import hx.com.example.rule.common.domain.ReferenceVariabDomain;
import hx.com.example.rule.dto.ReferenceVariabDTO;
import hx.com.example.rule.param.ReferenceVariabQueryParamDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-12-20 15:59
 */
@Component
public class ReferenceVariabDomainFactory extends AbstractDomainFactory<ReferenceVariabDomain,ReferenceVariabQueryParamDTO> {

//    @Autowired
    private ReferenceVariabMapperDAO referenceVariabMapperDAO;

    @Autowired
    private AutowiredDomainFactory autowiredDomainFactory;

    @Override
    public ReferenceVariabDomain create() {
        return null;
    }

    @Override
    public ReferenceVariabDomain active(String domainId) {
        return null;
    }

    @Override
    public List<ReferenceVariabDomain> activeList(ReferenceVariabQueryParamDTO paramDTO) {
        List<ReferenceVariabDTO> referenceVariabDTOS = null;
        List<ReferenceVariabDomain> referenceVariabDomains = new ArrayList<>();
        if (paramDTO == null){
//            referenceVariabDTOS = referenceVariabMapperDAO.selectAll();
        }else {
//            referenceVariabDTOS = referenceVariabMapperDAO.findDynamicConditional(paramDTO);
        }
        if (CollectionUtils.isEmpty(referenceVariabDTOS)){
            return referenceVariabDomains;
        }
        for (ReferenceVariabDTO dto : referenceVariabDTOS){
            ReferenceVariabDomain domain = autowiredDomainFactory.create(ReferenceVariabDomain.class);
            BeanUtils.copyProperties(dto,domain);
            referenceVariabDomains.add(domain);
        }
        return referenceVariabDomains;
    }

    @Override
    public Boolean store(ReferenceVariabDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(ReferenceVariabDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(List<ReferenceVariabDomain> domains) {
        return null;
    }
}
