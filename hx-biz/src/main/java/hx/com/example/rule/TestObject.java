package hx.com.example.rule;

import com.alibaba.fastjson.JSONArray;
import hx.com.example.rule.facade.ReferenceVariabFacade;
import hx.com.example.rule.facade.RuleExecuteFacade;
import hx.com.example.rule.info.ReferenceVariabInfo;
import hx.com.example.rule.order.ReferenceVariabOrder;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-12-12 17:27
 */
public class TestObject {

    @Resource
    private ReferenceVariabFacade referenceVariabFacade;

    @Autowired
    private RuleExecuteFacade ruleExecuteFacade;

    @NotBlank
    private String name;

    public void  testCreate(){
        ReferenceVariabOrder order = new ReferenceVariabOrder();
        order.setPackageName("hx.com.rule.test");
        List<ReferenceVariabInfo> resultInfo = referenceVariabFacade.queryReferenceVariab(order);
        System.out.println("-------------"+ JSONArray.toJSONString(resultInfo));
    }

}
