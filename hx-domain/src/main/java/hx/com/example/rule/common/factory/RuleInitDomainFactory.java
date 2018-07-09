package hx.com.example.rule.common.factory;

import hx.com.example.rule.common.domain.ReferenceVariabDomain;
import hx.com.example.rule.common.domain.RuleDomain;
import hx.com.example.rule.common.domain.RuleDrlDomain;
import hx.com.example.rule.common.domain.RuleInitDomain;
import hx.com.example.rule.param.RuleInitQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2017-12-19 15:54
 */
@Component
public class RuleInitDomainFactory extends AbstractDomainFactory<RuleInitDomain,RuleInitQueryParam> {

    @Autowired
    private AutowiredDomainFactory autowiredDomainFactory;

    @Autowired
    private RuleDrlDomainFactory ruleDrlDomainFactory;

    @Autowired
    private RuleDomainFactory ruleDomainFactory;

    @Autowired
    private ReferenceVariabDomainFactory referenceVariabDomainFactory;

    @Override
    public RuleInitDomain create() {
        return autowiredDomainFactory.create(RuleInitDomain.class);
    }

    @Override
    public RuleInitDomain active(String domainId) {
        return null;
    }

    @Override
    public List<RuleInitDomain> activeList(RuleInitQueryParam param) {
        List<RuleDrlDomain> ruleDrlDomainList = ruleDrlDomainFactory.activeList(null);
        List<RuleDomain> ruleDomainList = ruleDomainFactory.activeList(null);
        List<ReferenceVariabDomain> referenceVariabDomains = referenceVariabDomainFactory.activeList(null);
        List<RuleInitDomain> ruleInitDomains = active(ruleDrlDomainList,ruleDomainList,referenceVariabDomains);
        return ruleInitDomains;
    }

    @Override
    public Boolean store(RuleInitDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(RuleInitDomain domain) {
        return null;
    }

    @Override
    public Boolean reStore(List<RuleInitDomain> domain) {
        return null;
    }

    private List<RuleInitDomain> active(List<RuleDrlDomain> ruleDrlDomainList, List<RuleDomain> ruleDomainList,
                                        List<ReferenceVariabDomain> referenceVariabDomains){
        List<RuleInitDomain> ruleInitDomains = new ArrayList<>();
        RuleInitDomain ruleInitDomain = autowiredDomainFactory.create(RuleInitDomain.class);
        Map<String,List<RuleDomain>> ruleMap = new HashMap<>();
        Map<String,List<String>> variablMap = new HashMap<>();
        for (RuleDomain domain : ruleDomainList){
            if (ruleMap.containsKey(domain.getRulePackage())){
                List<RuleDomain> ruleDTOList = ruleMap.get(domain.getRulePackage());
                ruleDTOList.add(domain);
                ruleMap.put(domain.getRulePackage(),ruleDTOList);
            }else {
                List<RuleDomain> ruleDTOList = new ArrayList<>();
                ruleDTOList.add(domain);
                ruleMap.put(domain.getRulePackage(),ruleDTOList);
            }
        }

        for (ReferenceVariabDomain domain : referenceVariabDomains){
            if (variablMap.containsKey(domain.getPackageName())){
                List<String> importList = variablMap.get(domain.getPackageName());
                importList.add(domain.getVariabName());
                variablMap.put(domain.getPackageName(),importList);
            }else {
                List<String> importList  = new ArrayList<>();
                importList.add(domain.getVariabName());
                variablMap.put(domain.getPackageName(),importList);
            }
        }

        Map<String,RuleDrlDomain> ruleDrlDomainMap = new HashMap<>(ruleDrlDomainList.size());
        for (RuleDrlDomain domain : ruleDrlDomainList){
            domain.setRuleDomains(ruleMap.get(domain.getPackageName()));
            domain.setImportList(variablMap.get(domain.getPackageName()));
            ruleDrlDomainMap.put(domain.getPackageName(),domain);
        }

        ruleInitDomain.setRuleManageDomains(ruleDrlDomainMap);
        ruleInitDomains.add(ruleInitDomain);
        return ruleInitDomains;
    }
}
