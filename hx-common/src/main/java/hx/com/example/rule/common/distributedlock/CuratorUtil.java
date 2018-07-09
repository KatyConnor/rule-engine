package hx.com.example.rule.common.distributedlock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @Author mingliang
 * @Date 2018-04-25 15:36
 *
 * <p>
 *     实现分布式锁
 * </p>
 */
//@Component
public class CuratorUtil implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorUtil.class);

    @Value("${zookeeper.connection.url}")
    private String zookeeperConnectionString;

    @Value("${zookeeper.lockPath.prefix}")
    private String lockPathPrefix;

    private CuratorFramework client;

    @Override
    public void afterPropertiesSet() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();
    }

    /**
     * 获取锁。返回不为null表示成功获取到锁，用完之后需要调用releaseLock方法释放
     * @param relativePath 锁的相对路径，Not start with '/'
     * @param waitSeconds 等待秒数
     * @return 未获取到锁返回null
     */
    public InterProcessMutex getLock(String relativePath, int waitSeconds) {
        InterProcessMutex lock = new InterProcessMutex(client, lockPathPrefix + relativePath);
        try {
            if (lock.acquire(waitSeconds, TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (Exception e) {
            LOGGER.error("get lock error", e);
        }

        releaseLock(lock);
        return null;
    }

    /**
     * 释放锁
     * @param lock
     */
    public void releaseLock(InterProcessMutex lock) {
        LOGGER.info("release lock");
        if (lock != null && lock.isAcquiredInThisProcess()) {
            try {
                lock.release();
            } catch (Exception e) {
                LOGGER.warn("release lock error", e);
            }
        }
    }
}

