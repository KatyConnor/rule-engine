package hx.com.example.rule.common.distributedlock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 分布式锁抽象类
 * @Author mingliang
 * @Date 2018-04-25 16:35
 */
public abstract class AbstractDistributedJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDistributedJob.class);

    /** 至少锁60秒 */
    private static final long LOCK_MIN_TIME = 60000;

    @Resource
    private CuratorUtil curatorUtil;

    public void run() {
        InterProcessMutex lock = curatorUtil.getLock(getClass().getSimpleName() + "/lock", 1);
        if (lock == null) {
            LOGGER.info("can not get lock, exit job.");
            return;
        }

        long st = System.currentTimeMillis();
        LOGGER.info("start job...");

        try {
            process();
        } catch (Exception e) {
            LOGGER.error("job error", e);
        } finally {
            long cost = System.currentTimeMillis() - st;
            LOGGER.info("job finished, cost {} ms.", cost);

            if (cost < LOCK_MIN_TIME) {
                try {
                    Thread.sleep(LOCK_MIN_TIME - cost);
                } catch (InterruptedException e) {}
            }
            curatorUtil.releaseLock(lock);
        }
    }

    public abstract void process() throws Exception;

}
