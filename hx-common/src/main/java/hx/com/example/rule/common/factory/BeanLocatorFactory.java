package hx.com.example.rule.common.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @Author mingliang
 * @Date 2018-01-09 17:43
 */
@Component
public class BeanLocatorFactory implements BeanFactoryAware {

    private static BeanFactory beanFactory = null;
    private static BeanLocatorFactory beanLocatorFactory = null;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        beanLocatorFactory.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory(){
        return beanFactory;
    }

    public static BeanLocatorFactory getInstance(){
        if (beanLocatorFactory == null){
            beanLocatorFactory = (BeanLocatorFactory) beanFactory.getBean("beanLocator");
        }
        return beanLocatorFactory;
    }

    public static Object getBean(String beanName){
        return beanFactory.getBean(beanName);
    }

    public static Object getBean(Class<?> clazz){
        return beanFactory.getBean(clazz);
    }

}
