package hx.com.example.rule.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hc.ala.scheduler.facade.DubboTestFacade;
import hx.com.example.rule.facade.DubboServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author mingliang
 * @Date 2018-04-22 22:43
 */
@Component
public class DubboServiceFacadeImpl implements DubboServiceFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(DubboServiceFacadeImpl.class);

//    @Reference(interfaceClass = DubboTestFacade.class,version = "1.0.1")
//    private DubboTestFacade dubboTestFacade;

    @Override
    public String message() {
//        LOGGER.info("dubbo 调用处理成功，下面调用服务方的服务！");
//        String result = dubboTestFacade.messageTest();
//        LOGGER.info("result = {}",result);
        return "调用处理成功！";
    }
}
