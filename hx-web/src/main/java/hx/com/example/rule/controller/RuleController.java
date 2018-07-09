package hx.com.example.rule.controller;

import com.alibaba.fastjson.JSONObject;
import hx.com.example.rule.common.enums.HttpTypeEnum;
import hx.com.example.rule.common.info.HttpResponseInfo;
import hx.com.example.rule.common.utils.ContentType;
import hx.com.example.rule.facade.RuleExecuteFacade;
import hx.com.example.rule.order.RuleExcuteOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * 规则执行器
 *
 * @Author mingliang
 * @Date 2017-12-15 15:06
 */
@RestController
@RequestMapping("/ruleExecute")
public class RuleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleController.class);

    @Autowired
    private RuleExecuteFacade ruleExecuteFacade;

    @RequestMapping(value = "/executeRule",method = RequestMethod.POST)
    public HttpResponseInfo executeRule(@RequestBody String form){
        LOGGER.info("执行规则！requestParam = [{}]",form);

        JSONObject param = JSONObject.parseObject(form);
        String packageName = String.valueOf(param.get("packageName"));
        String url = String.valueOf(param.get("url"));
        String group = String.valueOf(param.get("group"));
        String requestMethod = String.valueOf(param.get("requestMethod"));
        Boolean asyn = Boolean.valueOf(String.valueOf(param.get("asyn")));
        JSONObject data = (JSONObject) param.get("data");

        RuleExcuteOrder order = new RuleExcuteOrder();
        order.setPackageName(packageName);
        order.setUrl(url);
        order.setGroup(group);
        order.setRequestMethod(HttpTypeEnum.getByCode(requestMethod));
        order.setData(data);
        order.setAsyn(asyn);
        HttpResponseInfo responseInfo = ruleExecuteFacade.executeRule(order);
        return responseInfo;
    }

    @RequestMapping(value = "/reload",method = RequestMethod.GET)
    public String reloadRule(){
        LOGGER.info("重新加载规整！");
        ruleExecuteFacade.reloadRule();
        return "加载成功！！";
    }

    @RequestMapping(value = "/myBoke",method = RequestMethod.GET,produces = {ContentType.APPLICATION_JSON_UTF_8,
            ContentType.TEXT_XML_UTF_8 })
    public ModelAndView myBoke(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myindex");
        return modelAndView;
    }

}
