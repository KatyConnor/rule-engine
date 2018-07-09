package hx.com.example.rule.common.domain;

import hx.com.example.rule.common.event.RuleAsynNoticeEvent;
import hx.http.client.service.HttpClientService;
import hx.thread.executor.pool.ThreadPoolService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-01-29 14:32
 */
@Getter
@Setter
public class RuleThenDomian extends Domain {

    @Autowired
    private ThreadPoolService threadPoolService;

    @Autowired
    private HttpClientService httpClientService;

    /*
     *   两种方式返回规则结果
     *   1、 将需要的结果返回
     *   2、 返回规则执行结果，true： 成功执行，执行结果异步通知， false： 执行失败，不发送异步通知
     */
    public void excuteRsult(Map map, List<String> keyList, boolean asyn){
//        RuleAsynNoticeEvent event = new RuleAsynNoticeEvent();
//        threadPoolService.execute(event);
    }

    /**
     * 逻辑代码那么配置
     */
}
