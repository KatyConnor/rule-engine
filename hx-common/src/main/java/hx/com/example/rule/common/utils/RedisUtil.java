package hx.com.example.rule.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author mingliang
 * @Date 2018-05-03 11:28
 */
@Component
public class RedisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(Object key,long time){
        try {
            if(time <= 0){
                return false;
            }
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            LOGGER.info("set expire time failed ,{}",e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(Object key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(Object key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            LOGGER.info("Judge whether the key value exists Exception ,{}",e);
            return false;
        }
    }

    /**
     * 根据键值删除缓存
     * @param key 可以传一个值 或多个
     */
    public void del(Object ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    //============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(Object key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(Object key,Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LOGGER.info("Key value in cache failure {}",e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(Object key,Object value,long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info("Key value in cache failure {}",e);
            return false;
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几的递增因子(大于0)
     * @return
     */
    public long incr(Object key, long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几的递减因子(大于0的正整数)
     * @return
     */
    public long decr(Object key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0的正整数");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //================================Map=================================
    /**
     * 根据键值，选项获取对应的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object getHashObject(Object key,Object item){
        if (null != key && null != item){
            return redisTemplate.opsForHash().get(key, item);
        }
        throw new RuntimeException(String.format("Key and options are not null, key = [s%], item = [s%]",key,item));
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object,Object> getHashMap(Object key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * map键值对放入缓存
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean putAllForHash(Object key, Map<Object,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            LOGGER.info("Key map in cache failure {}",e);
            return false;
        }
    }

    /**
     * map放入缓存并设置失效时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)，失效时间
     * @return true成功 false失败
     */
    public boolean putAllForHashAndTime(Object key, Map<Object,Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info("Key map in cache failure {}",e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean putForHash(Object key,Object item,Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            LOGGER.info("Key and item and value for hash in cache failure {}",e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建 ,并且设置时间
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean putForHashAndTime(String key,String item,Object value,long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info("Key and item and value for hash in cache failure {}",e);
            return false;
        }
    }

    /**
     * 删除hash表中指定键和选项的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void deleteForHash(Object key, Object... item){
        if (null != key && null != item){
            redisTemplate.opsForHash().delete(key,item);
        }
        throw new RuntimeException(String.format("Key and options are not null, key = [s%], item = [s%]",key,item));
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(Object key, Object item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param delta 要增加的递增因子(大于0，正数)
     * @return
     */
    public double hincr(Object key, Object item,double delta){
        return redisTemplate.opsForHash().increment(key, item, delta);
    }

    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param delta 要减少的递减因子(大于0的正数)
     * @return
     */
    public double hdecr(String key, String item,double delta){
        return redisTemplate.opsForHash().increment(key, item,-delta);
    }

    //============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    public Set<Object> getForSet(Object key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            LOGGER.info("Failure to obtain values based on keys ，{}",e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean hasKeyForSet(Object key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long addForSet(Object key, Object...values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return 0;
        }
    }

    /**
     * 将数据放入set缓存 ，并设置失效时间
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(Object key,long time,Object...values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if(time>0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long getSizeForSet(Object key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return 0;
        }
    }

    /**
     * 根据key和value 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(Object key, Object ...values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return 0;
        }
    }

    //===============================list=================================

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> getList(Object key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return null;
        }
    }

    /**
     * 获取list缓存的内容
     * @param key 键
     * @return
     */
    public List<Object> getList(Object key){
        try {
            return getList(key,0,0);
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long getListSize(Object key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object getForListIndex(Object key,long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean rightPpush(Object key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean rightPushAndTime(Object key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean rightPushAll(Object key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(Object key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean updateIndex(Object key, long index,Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long removeForList(Object key,long count,Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            LOGGER.info("Failure {}",e);
            return 0;
        }
    }

}
