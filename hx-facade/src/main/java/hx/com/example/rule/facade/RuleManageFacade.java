package hx.com.example.rule.facade;

import hx.com.example.rule.info.MenuInfo;
import hx.com.example.rule.order.KieContainerOrder;
import hx.com.example.rule.order.MenuQueryOrder;
import org.kie.api.runtime.KieContainer;

import java.util.List;

/**
 * 管理规则引擎
 * @Author mingliang
 * @Date 2017-12-18 17:20
 */
public interface RuleManageFacade {

    /**
     * 获取 KieContainer
     * @param order 规则包名
     * @return
     */
    KieContainer getKieContainer(KieContainerOrder order);

    /**
     * 查询菜单
     * @param order 查询条件
     * @return 返回条件
     */
    List<MenuInfo> getMenuInfos(MenuQueryOrder order);
}
