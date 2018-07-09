package hx.com.example.rule.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author mingliang
 * @Date 2018-02-05 16:17
 */
public class CollectionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtils.class);

    /**
     * 复制对象属性
     * @param source 源对象
     * @param target 目标对象
     * @param clazz 泛型类型
     */
    public static void copy(Collection<?> source, Collection target, Class<?> clazz){
        copy(source,target,clazz,null);
    }

    /**
     * 复制对象属性
     * @param source 源对象
     * @param target 目标对象
     * @param clazz  泛型类型
     * @param propertites 自定不同属性对象赋值
     */
    public static void copy(Collection<?> source, Collection target, Class<?> clazz, Map<String,String> propertites){
        for (Object object : source){
            try {
                Object targetObject = clazz.newInstance();
                BeanUtils.copyProperties(object,targetObject);
                if (null != propertites && propertites.size() > 0){
                    setPropertites(object,targetObject,propertites);
                }
                target.add(targetObject);
            } catch (InstantiationException e) {
                LOGGER.error("赋值对象的属性值失败 ！ InstantiationException = {}",e);
            } catch (IllegalAccessException e) {
                LOGGER.error("赋值对象的属性值失败 ！ IllegalAccessException = {} ",e);
            } catch (NoSuchFieldException e) {
                LOGGER.error("赋值对象的属性值失败 ！ NoSuchFieldException = {} ",e);
             }
        }
    }

    /**
     * 对指定的特殊属性字段赋值
     * @param object
     * @param target
     * @param propertites
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static void setPropertites(Object object,Object target,Map<String,String> propertites)
            throws IllegalAccessException, NoSuchFieldException {

        Field[] sourceFields = object.getClass().getDeclaredFields();
        Map<String,Object> targetMap = getCollectionValue(target);
        Map<String,Field> fieldMap = getTargetListField(target);

        Map<String,Object> valueMap = new HashMap<>();
        for (Map.Entry entry : propertites.entrySet()){
            for (Field field : sourceFields){
                if (entry.getKey().equals(field.getName())){
                    field.setAccessible(true);
                    String name = field.getType().getSimpleName();
                    switch (name){
                        case "List":
                            Collection<?> collection = (Collection<?>) field.get(object);
                            if (null != collection && collection.size() >0){
                                Class<?> type = getListType(fieldMap.get(String.valueOf(entry.getValue())));
                                copy(collection,(Collection) targetMap.get(String.valueOf(entry.getValue())),
                                        type,propertites);
                                valueMap.put(String.valueOf(entry.getValue()),targetMap.get(String.valueOf(entry.getValue())));
                            }
                            break;
                        case "Map":
                            break;
                        case "":
                            break;
                        default:
                            valueMap.put(String.valueOf(entry.getValue()),field.get(object));
                            break;

                    }
                }
            }
        }
        Field[] targetFields = target.getClass().getDeclaredFields();
        setSupperClass(target,target.getClass().getSuperclass(),valueMap);
        setPropertites(target,targetFields,valueMap);
    }

    /**
     * 获取对象中是list集合的对象，当属性为null的时候进行初始化
     * @param target
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String,Object> getCollectionValue(Object target) throws IllegalAccessException {
        Field[] fields = target.getClass().getDeclaredFields();
        Map<String,Object> typeMap = new HashMap<>();
        for (Field field : fields){
            if ("List".equals(field.getType().getSimpleName())){
                field.setAccessible(true);
                Collection<?> collection = (Collection<?>) field.get(target);
                if (null == collection || collection.size() <= 0){
                    collection = new ArrayList<>();
                }
                typeMap.put(field.getName(),collection);
            }
        }
        return typeMap;
    }

    /**
     * 获取目标对象中是list集合的属性字段
     * @param target
     * @return
     */
    private static Map<String,Field> getTargetListField(Object target){
        Field[] fields = target.getClass().getDeclaredFields();
        Map<String,Field> fieldMap = new HashMap<>();
        for (Field field : fields){
            if ("List".equals(field.getType().getSimpleName())){
                fieldMap.put(field.getName(),field);
            }
        }
        return fieldMap;
    }

    /**
     * 获取父类中是否有需要赋值的字段，存在则将字段赋值到目标对象
     * @param object
     * @param supperClass
     * @param valueMap
     * @throws IllegalAccessException
     */
    private static void setSupperClass(Object object,Class<?> supperClass, Map<String,Object> valueMap) throws IllegalAccessException{
        if (null == object || "Object".equals(supperClass.getSimpleName()) ||
                "Class".equals(supperClass.getSimpleName())){
            return;
        }
        Field[] fields = supperClass.getDeclaredFields();
        if (null != supperClass.getSuperclass()){
            setSupperClass(object,supperClass.getSuperclass(),valueMap);
        }
        setPropertites(object,fields,valueMap);
    }


    /**
     * 给属性值赋值
     * @param object
     * @param fields
     * @param valueMap
     * @throws IllegalAccessException
     */
    private static void setPropertites(Object object,Field[] fields,Map<String,Object> valueMap) throws IllegalAccessException{
        for (Map.Entry entry : valueMap.entrySet()){
            for (Field field : fields){
                if (entry.getKey().equals(field.getName())){
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    switch (fieldType.getSimpleName()){
                        case "long":
                            field.set(object,Long.valueOf(String.valueOf(entry.getValue())));
                            break;
                        case "Long":
                            field.set(object,Long.valueOf(String.valueOf(entry.getValue())));
                            break;
                        case "String":
                            field.set(object,String.valueOf(entry.getValue()));
                            break;
                        case "List":
                            field.set(object,entry.getValue());
                            break;
//                        case "Integer":
//                        case "int":
//                        case "Double":
//                        case "double":
                        default:
                            field.set(object,entry.getValue());
                            break;
                    }
                }
            }
        }
    }

    /***
     * 获取List中的泛型类型
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public static Class<?> getListType(Field field) throws NoSuchFieldException, SecurityException {
        Type type = field.getGenericType();
        if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
            for (Type type1 : ((ParameterizedType) type).getActualTypeArguments()) {
                return (Class<?>)type1;
            }
        }
        return null;
    }
}
