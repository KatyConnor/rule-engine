package hx.com.example.rule.common.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * 判断的代理是cglib，jdk，aop
 * @Author mingliang
 * @Date 2018-01-11 15:34
 */
public class ProxyUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyUtils.class);

    /**
     * 判断代理是 aop、cglib、jdk 代理
     * @param beanInstance
     * @return
     */
    public static Object getTarget(Object beanInstance){
        if (!AopUtils.isAopProxy(beanInstance)){
            return beanInstance;
        }else if (AopUtils.isCglibProxy(beanInstance)){
            try {
                Field field = beanInstance.getClass().getDeclaredField("CGLIB$CALLBACK_0");
                field.setAccessible(true);
                Object dynamicAdvisedInterceptor = field.get(beanInstance);
                Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
                advised.setAccessible(true);
                Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
                return target;
            }catch (NoSuchFieldException e) {
                LOGGER.info("{}",e);
            } catch (IllegalAccessException e){
                LOGGER.info("{}",e);
            } catch (Exception e) {
                LOGGER.info("{}",e);
            }
        }
        return null;
    }
}
