package hx.com.example.rule.common.proxy;

import hx.com.example.rule.common.abstractclass.AbstractProxy;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 *  BeanGenerator 动态代理生成一个代理类
 * @Author mingliang
 * @Date 2018-01-24 11:09
 */
@Component
public class BeanGeneratorProxy extends AbstractProxy{

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanGeneratorProxy.class);

    private final static String UNDER_LINE = "_";

    @Override
    public <T> T createBean(Map<String, Object> properties, Map<String,Class<?>> clazzType,Class<T> superClass) {
        T object = null;
        try {
            object = newInstance(properties,clazzType,superClass);
        } catch (IllegalAccessException e) {
            LOGGER.error("生成代理对象失败！ IllegalAccessException = {} ",e);
        } catch (InstantiationException e) {
            LOGGER.error("生成代理对象失败！ InstantiationException = {} ",e);
        }
        setValue(object,properties);
        return object;
    }

    private <T> T newInstance(Map<String, Object> properties,Map<String,Class<?>> clazzType,Class<?> superClass) throws IllegalAccessException, InstantiationException {
        BeanGenerator beanGenerator = new BeanGenerator();
        for (Map.Entry entry : properties.entrySet()){
            beanGenerator.addProperty(String.valueOf(entry.getKey()),clazzType.get(entry.getKey()));
        }

        beanGenerator.setSuperclass(superClass);
        T obj =  (T) beanGenerator.create();
        BeanCopier copier = BeanCopier.create(superClass,obj.getClass(),false);
        copier.copy(superClass.newInstance(),obj,null);
        return obj;
    }

    private void setValue(Object obj,Map<String, Object> fieldValue){
        Field[] cglibFields = obj.getClass().getDeclaredFields();
        for (Field field : cglibFields){
            field.setAccessible(true);
            String[] keys = field.getName().split(UNDER_LINE);
            String key = keys[keys.length-1];
            if (fieldValue.containsKey(key)){
                String str = String.valueOf(fieldValue.get(key));
                try {
                    field.set(obj,str);
                } catch (IllegalAccessException e) {
                    LOGGER.info("filed to add field value");
                }
            }
        }
    }
}
