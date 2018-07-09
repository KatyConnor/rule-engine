package hx.com.example.rule.facade;


import hx.com.example.rule.info.ReferenceVariabInfo;
import hx.com.example.rule.order.ReferenceVariabOrder;

import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-01-09 14:08
 */
public interface ReferenceVariabFacade {

    /**
     * 查询变量
     * @param order
     * @return
     */
    List<ReferenceVariabInfo> queryReferenceVariab(ReferenceVariabOrder order);
}
