package hx.com.example.rule.common.factory;

import hx.com.example.rule.common.domain.RuleInitDomain;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2017-12-19 16:13
 */
@Component
public class KieContainerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(KieContainerFactory.class);

    private static Map<String,KieContainer> kieContainerMap;
    private boolean init = false;

    @Autowired
    private RuleInitDomainFactory ruleInitDomainFactory;

    public KieContainer getKieContainer(String packageName){
        if (kieContainerMap == null || kieContainerMap.size() <0){
            initRule();
        }
        return kieContainerMap.get(packageName);
    }

    public void reloadRule(){
        init = false;
        initRule();
    }

    private void setKieContainerMap(Map<String,KieContainer> kieContainerMap){
        this.kieContainerMap = kieContainerMap;
    }

    private void initRule() {
        if (init){
            LOGGER.info("");
            return;
        }
        LOGGER.info("初始化规则引擎！");
        long startTime = System.currentTimeMillis();
        List<RuleInitDomain> domains =  ruleInitDomainFactory.activeList(null);
        RuleInitDomain domain = domains.get(0);
        if (domain == null){
            LOGGER.info("规则引擎启动失败！");
            return;
        }
        if (domain.init()){
            LOGGER.info("规则引擎启动成功！");
        }else {
            LOGGER.info("规则引擎启动失败！");
        }
        setKieContainerMap(domain.getKieContainerMap());
        LOGGER.info("耗时：{} ms",System.currentTimeMillis() - startTime);
        init = true;
    }
}
