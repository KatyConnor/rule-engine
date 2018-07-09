package hx.com.example.rule.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import hx.com.example.rule.common.redis.FastJsonRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * springboot 2.0.1 整合 Redis的方式
 * @Author mingliang
 * @Date 2018-05-02 11:15
 */
@Configuration
//@EnableCaching // 开启缓存
public class RedisConfig extends CachingConfigurerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//    @Value("${spring.redis.pass}")
//    private String password;
//
//    @Value("${spring.redis.database}")
//    private int database;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;

//    @Autowired
//    private RedisConnectionFactory connectionFactory;

    /**
     * 自定义的缓存key的生成策略
     *              若想使用这个key  只需要讲注解上keyGenerator的值设置为keyGenerator即可</br>
     * @return 自定义策略生成的key
     */
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                LOGGER.info("Redis 初始化键值！");
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }

    /**
     * 自定义key.
     * key --> 项目名 + 缓存空间值 + 所有参数的值
     * 即使@Cacheable中的value属性一样，key也会不一样。
     */
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator(){
//            @Override
//            public String generate(Object o, Method method, Object... objects) {
//                // This will generate a unique key of the class name, the method name
//                //and all method parameters appended.
//                StringBuilder sb = new StringBuilder();
//                sb.append(contextPath).append("/:");
//                Cacheable cacheable = method.getAnnotation(Cacheable.class);
//                CachePut cachePut = method.getAnnotation(CachePut.class);
//                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
//                if (cacheable != null) {
//                    sb.append(Arrays.toString(cacheable.value())).append(":");
//                }else if (cachePut != null) {
//                    sb.append(Arrays.toString(cachePut.value())).append(":");
//                }else if (cacheEvict != null) {
//                    sb.append(Arrays.toString(cacheEvict.value())).append(":");
//                }
//                Map valueMap = new HashMap();
//                for (Object obj : objects) {
//                    try {
//                        getStringValueMap(obj,valueMap);
//                    } catch (IllegalAccessException e) {
//                        logger.info("生成key的时候,[{}]转换map异常，生成的key丢弃了该值。",obj.getClass(),e);
//                    }
//                }
//                sb.append(valueMap.toString());
//                System.err.println(sb.toString());
//                return sb.toString();
//            }
//        };
//    }

    /**
     * <p>
     *     和之前的RedisTemplate 初始化的区别
     * </p>
     * @param
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
        LOGGER.info("Redis 初始化 RedisCacheManager! ");
        return RedisCacheManager.create(connectionFactory);
    }


    /**
     *
     * @param
     * @return
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory){
        LOGGER.info(" 初始化 RedisTemplate ");
        RedisTemplate<?,?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        setSerializer(redisTemplate);//设置序列化工具
        redisTemplate.afterPropertiesSet();
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    private void setSerializer(RedisTemplate<?,?> redisTemplate){
        LOGGER.info("对 key 和 value 进行 序列化 ");
        //key序列化方式;但是如果方法上有Long等非String类型的话，会报类型转换错误； 序列化和反序列化redis的key值
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        // 采用  FastJson 来序列化和反序列化redis的value值
//        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
//        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory connectionFactory) {
        LOGGER.info(" Redis’s StringRedisTemplate 初始化   ");
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        return stringRedisTemplate;
    }


    /**
     * Redis集群的配置
     * @return RedisClusterConfiguration
     * @autor lpl
     * @date 2017年12月22日
     * @throws
     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        //Set<RedisNode> clusterNodes
//        String[] serverArray = clusterNodes.split(",");
//
//        Set<RedisNode> nodes = new HashSet<RedisNode>();
//
//        for(String ipPort:serverArray){
//            String[] ipAndPort = ipPort.split(":");
//            nodes.add(new RedisNode(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1])));
//        }
//
//        redisClusterConfiguration.setClusterNodes(nodes);
//        redisClusterConfiguration.setMaxRedirects(mmaxRedirectsac);
//
//        return redisClusterConfiguration;
//    }

    /**
     * 配置redis的哨兵,使用的时候可以这样配置
     * @return RedisSentinelConfiguration
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
//    @Bean
//    public RedisSentinelConfiguration sentinelConfiguration(){
//        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
//        //配置matser的名称
//        RedisNode redisNode = new RedisNode(host, port);
//        redisNode.setName("mymaster");
//        redisSentinelConfiguration.master(redisNode);
//        //配置redis的哨兵sentinel
//        RedisNode senRedisNode = new RedisNode(senHost1,senPort1);
//        Set<RedisNode> redisNodeSet = new HashSet<>();
//        redisNodeSet.add(senRedisNode);
//        redisSentinelConfiguration.setSentinels(redisNodeSet);
//        return redisSentinelConfiguration;
//    }

    // ----------------------         其他补充          ------------------------------------
//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//                                            MessageListenerAdapter listenerAdapter) {
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
//
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
//
//    @Bean
//    Receiver receiver(CountDownLatch latch) {
//        return new Receiver(latch);
//    }
//
//    @Bean
//    CountDownLatch latch() {
//        return new CountDownLatch(1);
//    }
//
//    public class Receiver {
//
//
//        private CountDownLatch latch;
//
//        @Autowired
//        public Receiver(CountDownLatch latch) {
//            this.latch = latch;
//        }
//
//        public void receiveMessage(String message) {
//            latch.countDown();
//        }
//    }


    /**
     * redis数据操作异常处理
     * 这里的处理：在日志中打印出错误信息，但是放行
     * 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得能够出问题时不用缓存
     * @return
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        LOGGER.info(" 异常捕获初始化  ");
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                LOGGER.error("redis异常：key=[{}]",key,e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                LOGGER.error("redis异常：key=[{}]",key,e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key)    {
                LOGGER.error("redis异常：key=[{}]",key,e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                LOGGER.error("redis异常：",e);
            }
        };
        return cacheErrorHandler;
    }
}
