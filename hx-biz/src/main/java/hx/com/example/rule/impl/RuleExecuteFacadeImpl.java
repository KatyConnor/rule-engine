package hx.com.example.rule.impl;

import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.domain.RuleDomain;
import hx.com.example.rule.common.domain.RuleParamDomain;
import hx.com.example.rule.common.enums.HttpCodeRnum;
import hx.com.example.rule.common.enums.HttpTypeEnum;
import hx.com.example.rule.common.factory.KieContainerFactory;
import hx.com.example.rule.common.factory.RuleDomainFactory;
import hx.com.example.rule.common.info.HttpEntity;
import hx.com.example.rule.common.info.HttpResponseInfo;
import hx.com.example.rule.common.proxy.BeanGeneratorProxy;
import hx.com.example.rule.facade.ReferenceVariabFacade;
import hx.com.example.rule.facade.RuleExecuteFacade;
import hx.com.example.rule.info.ReferenceVariabInfo;
import hx.com.example.rule.order.ReferenceVariabOrder;
import hx.com.example.rule.order.RuleExcuteOrder;
import hx.com.example.rule.param.RuleQueryParamDTO;
import hx.http.client.service.HttpClientService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 规则引擎 RHS 执行部分
 * @Author mingliang
 * @Date 2017-12-18 17:25
 */
@Service
public class RuleExecuteFacadeImpl implements RuleExecuteFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleExecuteFacadeImpl.class);

    @Autowired
    private RuleDomainFactory ruleDomainFactory;

    @Autowired
    private KieContainerFactory kieContainerFactory;

    @Override
    public HttpResponseInfo executeRule(RuleExcuteOrder order) {
        LOGGER.info("{}.executeRule 执行引擎的结果 RHS！,入参：{}",this.getClass().getName(), JSONObject.toJSONString(order));

        String packageName = order.getPackageName();
        String url = order.getUrl();
        String group = order.getGroup();
        HttpTypeEnum requestMethod = order.getRequestMethod();
        JSONObject object = order.getData();

        //1、获取kieSession
        KieContainer kieContainer = kieContainerFactory.getKieContainer(order.getPackageName());
        KieSession session = kieContainer.newKieSession();

        // 根据packageName 找到规则组下面有几个规则
        RuleQueryParamDTO paramDTO = new RuleQueryParamDTO();
        paramDTO.setRulePackage(packageName);
        paramDTO.setGroup(group);
        List<RuleDomain> ruleDomains = ruleDomainFactory.activeList(paramDTO);

        Map value = new HashMap<>();
        for (Map.Entry entry : object.entrySet()){
            value.put(entry.getKey(),entry.getValue());
        }
        value.put("url",url);
        value.put("requestMethod",requestMethod);
        value.put("result","false");

        for (int i = 0; i < ruleDomains.size(); i++){
            session.insert(value);
            session.fireAllRules();
        }
        session.destroy();

        // 返回处理结果
        HttpResponseInfo responseInfo = new HttpResponseInfo();
        HttpEntity httpEntity = new HttpEntity();
        JSONObject jsonObject = new JSONObject();
        if (order.getAsyn()){
            List<String> keyList = new ArrayList<>();
            for (RuleDomain domain : ruleDomains){
                 if (StringUtils.isNotBlank(domain.getResult())){
                    String resultStr = domain.getResult();
                    List list = null;
                    if (resultStr.indexOf(",") != -1){
                        list = new ArrayList<>(Arrays.asList(resultStr.split(",")));
                    }else {
                        list = new ArrayList<>();
                        list.add(resultStr);
                    }
                    keyList.addAll(list);
                }
            }

            boolean next = true;
            long startTime = System.currentTimeMillis();
            while ( next ){
                if (value.get("result").equals("true")){
                    if (CollectionUtils.isNotEmpty(keyList)){
                        Set<String> set = new HashSet<>(keyList);
                        Iterator<String> iterator = set.iterator();
                        while (iterator.hasNext()){
                            String key = iterator.next();
                            jsonObject.put(key,value.get(key));
                        }
                        httpEntity.setData(jsonObject.toJSONString());
                    }
                    httpEntity.setSuccess(true);
                    httpEntity.setCode("200");
                    responseInfo.setCode(HttpCodeRnum.SUCCESS.getCode());
                    responseInfo.setEntity(httpEntity);
                    responseInfo.setMessage("调用处理成功");
                    next = false;
                }else if (System.currentTimeMillis() - startTime > 1000 * 30){
                    // 设置等待处理结果的时间,等待30s 还有处理完直接退出
                    next = false;
                    responseInfo.setCode(HttpCodeRnum.FAILED.getCode());
                    responseInfo.setMessage("等待处理结果超时！");
                }
            }
            return responseInfo;
        }

        httpEntity.setSuccess(true);
        httpEntity.setCode("200");
        responseInfo.setCode(HttpCodeRnum.SUCCESS.getCode());
        responseInfo.setEntity(httpEntity);
        responseInfo.setMessage("调用处理成功");

        return responseInfo;
    }

    @Override
    public void reloadRule() {
        LOGGER.info("重新加载规则引擎！");
        kieContainerFactory.reloadRule();
    }


}
