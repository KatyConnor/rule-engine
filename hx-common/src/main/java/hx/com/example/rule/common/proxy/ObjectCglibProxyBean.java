package hx.com.example.rule.common.proxy;


import hx.com.example.rule.common.factory.BeanLocatorFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * cglib 代理
 *
 * @Author mingliang
 * @Date 2017-09-17 21:58
 */
@Component
public class ObjectCglibProxyBean implements MethodInterceptor{

    private final static Logger LOGGER = LoggerFactory.getLogger(ObjectCglibProxyBean.class);

    // 目标对象
    private Object target;

    public ObjectCglibProxyBean(){}

    public ObjectCglibProxyBean(Object object){
        this.target = object;
    }

    /**
     * 创建代理类对象
     * @param object 被代理对象
     * @return 代理类对象
     */
    public  <T> T createObject(Class<T> object,Class[] types, Object[] args){
        //利用Enhancer来创建代理类
        Enhancer enhancer=new Enhancer();
        //为目标对象指定父类
        enhancer.setSuperclass(object);
        //设置回调函数
        enhancer.setCallback(this);
        //返回生成的代理类
        T t = null;
        if (args !=null && types != null && args.length > 0 && types.length >0 ){
            t = (T) enhancer.create(types,args);
        }else {
            t =  (T) enhancer.create();
        }
        try {
            t = (T) setFiledValue(t);
        } catch (IllegalAccessException e) {
            LOGGER.error("初始化代理对象失败！, {}",e);
        }
        return t;
    }





    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object intercept = methodProxy.invokeSuper(o, args);
        return intercept;
    }

    /**
     * <p>
     *     如果目标对象进行 new 得到则
     *     将目标对象的属性中使用了  Resource 或者 Autowired 注解的属性将
     *     从spring的ApplicationContext的上下文中获取该对属性在IOC中的对象
     *     地址赋值给当前属性（如果属性为null 进行设置操作，如果不为null，则
     *     不操作）
     * </p>
     * @param object 代理对象
     * @return 返回处理后的代理对象
     * @throws IllegalAccessException
     */
    private Object setFiledValue(Object object) throws IllegalAccessException {
        // 获取父类(目标对象)，cglib 代理是继承于目标对象进行扩展
        Class superClass = object.getClass().getSuperclass();
        //获取属性-->Field 
        Field[] fields = superClass.getDeclaredFields();
        for (Field field : fields ){
            field.setAccessible(true);
            Resource resource = field.getAnnotation(Resource.class);
            Autowired autowired = field.getAnnotation(Autowired.class);
            if (resource !=null || autowired != null){
                // 获取原来的值，判断是否为空
                Object value = field.get(object);
                if (null == value){
                    setValue(object,field);
                }
            }
        }
        return object;
    }

    private void setValue(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object obj = BeanLocatorFactory.getBean(field.getType());
        field.set(object,obj);
    }

}
