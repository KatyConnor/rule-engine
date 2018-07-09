package hx.com.example.rule.common.domain;

import hx.com.example.rule.common.utils.DroolsUtils;
import hx.com.example.rule.common.utils.ResourceWrapper;
import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author mingliang
 * @Date 2017-12-14 14:34
 */
public class RuleInitDomain extends Domain {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleInitDomain.class);

    private static final String RULESFILE_NAME = "rules.drl";
    private static final String ENTER = "; ";
    private static final String SPACE = " ";
    private static final String END = "end ";

    private static KieServices kieServices;
    private Map<String,KieContainer> kieContainerMap = new HashMap<>();
    private static Map<String,ReleaseId> releaseIdHashMap = new HashMap<>();

    private Map<String,RuleDrlDomain> ruleManageDomainMap;

    public Map<String,KieContainer> getKieContainerMap(){
        return this.kieContainerMap;
    }

    public void setRuleManageDomains(Map<String,RuleDrlDomain> ruleManageDomainMap){
        this.ruleManageDomainMap = ruleManageDomainMap;
    }

    public boolean init(){
        InitRuleUtils.init(this);
        return true;
    }

    private void getReleaseIds(){
        if (ruleManageDomainMap != null && ruleManageDomainMap.size() >0){
            for (Map.Entry entry : ruleManageDomainMap.entrySet()){
                RuleDrlDomain domain = (RuleDrlDomain) entry.getValue();
                ReleaseId releaseId = kieServices.newReleaseId(domain.getGroupId(),
                        domain.getArtifactId(), domain.getRuleVersion());
                releaseIdHashMap.put(domain.getPackageName(),releaseId);
            }
        }
    }

    private InternalKieModule getInternalKieModule(ReleaseId releaseId) throws IOException {
        return DroolsUtils.initKieJar(kieServices, releaseId);
    }

    private void ruleInit(){
        initKieServices();
        KieRepository repository = kieServices.getRepository();
        getReleaseIds();
        for (Map.Entry entry : releaseIdHashMap.entrySet()){
            try {
                ReleaseId releaseId = (ReleaseId)entry.getValue();
                InternalKieModule kjar = getInternalKieModule(releaseId);
                repository.addKieModule(kjar);
                KieContainer kieContainer = kieServices.newKieContainer(releaseId);
                //新增一个规则文件
                kjar = DroolsUtils.createKieJar(kieServices, releaseId,
                        new ResourceWrapper(ResourceFactory.newByteArrayResource(getRules((String) entry.getKey())),
                                RULESFILE_NAME));
                repository.addKieModule(kjar);
                kieContainer.updateToVersion(releaseId);
                kieContainerMap.put((String) entry.getKey(),kieContainer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // @TODO 其他复杂的规则暂时不加，此处先做一个简单规则，验证是否可行
    private byte[] getRules(String packageName){
        StringBuilder sb = new StringBuilder();
        RuleDrlDomain domain = ruleManageDomainMap.get(packageName);
        sb.append("package ").append(domain.getPackageName()).append(ENTER).append(getImports(domain.getImportList()));
        List<RuleDomain> ruleDomains = domain.getRuleDomains();
        List<String> ruleNames = new ArrayList<>();
        for (RuleDomain ruleDomain : ruleDomains){
            if (ruleNames.contains(ruleDomain.getRuleName())){
                LOGGER.info("同一个package 下面 ruleName 不能重复！");
                return new byte[0];
            }

            ruleNames.add(ruleDomain.getRuleName());
            sb.append("rule \"").append(ruleDomain.getRuleName()).append("\" when ").
                    append(ruleDomain.getWhen()).append(SPACE).append("then ").append(ruleDomain.getThen()).
                    append(ENTER).append(END);
        }
        LOGGER.info(sb.toString());
        return sb.toString().getBytes();
    }

    private String getImports(List<String> imports){
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : imports){
            stringBuilder.append("import ").append(str).append(ENTER);
        }
        return stringBuilder.toString();
    }

    private KieServices initKieServices(){
        if (kieServices == null){
            synchronized (this){
                if (kieServices == null){
                    kieServices = KieServices.Factory.get();
                }
            }
        }
        return kieServices;
    }

    private static final class InitRuleUtils{
        private static void init(RuleInitDomain domain){
            domain.ruleInit();
        }
    }

}
