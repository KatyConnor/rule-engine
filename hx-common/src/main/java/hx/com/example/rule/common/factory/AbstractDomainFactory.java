package hx.com.example.rule.common.factory;

import hx.com.example.rule.common.domain.Domain;
import hx.com.example.rule.common.param.BaseParam;
import hx.data.mybatis.param.PageParam;

import java.util.List;

/**
 * @Author mingliang
 * @Date 2017-08-31 10:29
 */

public abstract class AbstractDomainFactory<T extends Domain, E extends PageParam>{

    public abstract T create ();

    public abstract T active(String domainId);

    public abstract List<T> activeList(E param);

    public abstract  Boolean store(T domain);

    public abstract Boolean reStore(T domain);

    public abstract Boolean reStore(List<T> domains);
}
