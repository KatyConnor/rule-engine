package hx.com.example.rule.common.abstractclass;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-01-24 15:57
 */
public abstract class AbstractProxy {

    /**
     * 动态生成代理对象类，生成的代理对象将继承源对象
     * @param properties 属性和对应的值
     * @param clazzType  属性类型
     * @param superClass 被代理源对象
     * @return 返回代理对象
     */
    public abstract <T> T createBean(Map<String,Object> properties,Map<String,Class<?>> clazzType,Class<T> superClass);
}
