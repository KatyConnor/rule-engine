package hx.com.example.rule.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Author mingliang
 * @Date 2018-01-04 16:26
 */
public class BeanMapUtil {

    private static final String JAVAP = "java.";
    private static final String JAVADATESTR = "java.util.Date";

    /**
     * 对象转换成Map
     * @param bean
     * @return
     * @throws IllegalAccessException
     */
    public static <T> Map<String, Object> beanToMap(T bean) throws IllegalAccessException {
        Map<String, Object> map = Maps.newHashMap();
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(bean);
            map.put(fieldName, value);
        }
        return map;
    }

    /**
     * map转换成bean对象
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String,Object> map, T bean){
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>> 
     * @param beans
     * @param <T>
     * @return
     */
    public static <T> List<Map<String,Object>> beansToListMap(List<T> beans) throws IllegalAccessException {
        List<Map<String,Object>> listMap = Lists.newArrayList();
        if (CollectionUtils.isEmpty(beans)){
            return listMap;
        }
        for (T bean : beans){
            Map<String,Object> map  = beanToMap(bean);
            listMap.add(map);
        }
        return listMap;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T> 
     * @param listMap
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> listMapsToBeans(List<Map<String, Object>> listMap, Class<?> clazz) throws IllegalAccessException, InstantiationException {
        List<T> beans = Lists.newArrayList();
        if (listMap != null && listMap.size() > 0){
            for (Map<String, Object> valueMap :listMap) {
                T object= (T) clazz.newInstance();
                T bean = mapToBean(valueMap,object);
                beans.add(bean);
            }
        }
        return beans;
    }
}
