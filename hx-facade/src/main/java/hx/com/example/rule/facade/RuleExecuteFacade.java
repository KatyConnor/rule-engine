package hx.com.example.rule.facade;

import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.info.HttpResponseInfo;
import hx.com.example.rule.order.RuleExcuteOrder;

/**
 * 执行规则引擎
 * @Author mingliang
 * @Date 2017-12-18 17:25
 */
public interface RuleExecuteFacade {

    /**
     * 执行规则
     * @param order 执行参数
     * @return
     */
    HttpResponseInfo executeRule(RuleExcuteOrder order);

    /**
     * 重庆加载规则引擎
     */
    void reloadRule();

}
