package hx.com.example.rule.impl;

import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.domain.ReferenceVariabDomain;
import hx.com.example.rule.facade.ReferenceVariabFacade;
import hx.com.example.rule.common.factory.ReferenceVariabDomainFactory;
import hx.com.example.rule.info.ReferenceVariabInfo;
import hx.com.example.rule.order.ReferenceVariabOrder;
import hx.com.example.rule.param.ReferenceVariabQueryParamDTO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-01-09 14:15
 */
@Service
public class ReferenceVariabFacadeImpl implements ReferenceVariabFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceVariabFacadeImpl.class);

    @Autowired
    private ReferenceVariabDomainFactory referenceVariabDomainFactory;

    @Override
    public List<ReferenceVariabInfo> queryReferenceVariab(ReferenceVariabOrder order) {
        LOGGER.info("查询规则引入变量,入参：{}，", JSONObject.toJSONString(order));
        ReferenceVariabQueryParamDTO queryParamDTO = new ReferenceVariabQueryParamDTO();
        queryParamDTO.setPackageName(order.getPackageName());
        List<ReferenceVariabDomain> domainList = referenceVariabDomainFactory.activeList(queryParamDTO);
        if (CollectionUtils.isEmpty(domainList)){
            LOGGER.info("没有查询到相应的数据！");
            return null;
        }

        List<ReferenceVariabInfo> infos = new ArrayList<>(domainList.size());
        for (ReferenceVariabDomain domain : domainList){
            ReferenceVariabInfo info = new ReferenceVariabInfo();
            BeanUtils.copyProperties(domain,info);
            infos.add(info);
        }

        return infos;
    }
}
