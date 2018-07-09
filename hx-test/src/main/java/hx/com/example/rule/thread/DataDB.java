package hx.com.example.rule.thread;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author mingliang
 * @Date 2018-06-01 11:03
 */
public class DataDB {

    private static AtomicInteger count = new AtomicInteger();
    private static ThreadLocal<DataDB> threadLocal = new ThreadLocal<>();
    private static DataDB dataDB;

    private DataDB(){
    }

    public static DataDB getNewInstance(){
        dataDB = DataDB.DataDBFactory.NEW_INSTANCE;
        return dataDB;
    }

    public int getCount(){
        return count.get();
    }

    public void excute(String message,long millis) throws InterruptedException {
        Thread.sleep(millis);
        System.out.println(String.format("count = %s, message = %s",count,message));
        count.addAndGet(1);
    }

    private static final class DataDBFactory{
        private static final DataDB NEW_INSTANCE = new DataDB();
    }
}
