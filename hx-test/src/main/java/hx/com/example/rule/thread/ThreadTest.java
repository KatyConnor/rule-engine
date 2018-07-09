package hx.com.example.rule.thread;

/**
 * @Author mingliang
 * @Date 2018-06-01 11:00
 */
public class ThreadTest {

    public static void main(String[] args) {
        // 测试并发情况下，单列对象被多个使用，去访问去修改同一个数据是否存在脏读的情况
        final DataDB[] dataDBNext = {null};
        for (int i =0; i <100; i++){
            int miss = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100-miss);
                        DataDB dataDB = DataDB.getNewInstance();
                        if ( dataDBNext[0] == null){
                            dataDBNext[0] = dataDB;
                            System.out.println("dataDBNext[0] ==== "+dataDBNext[0]);
                        }
                        System.out.println(dataDB);
                        dataDB.excute(String.format("执行第 %s 个",miss),miss);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        while (dataDBNext[0] == null || dataDBNext[0].getCount() < 100){
           if (dataDBNext[0] == null || dataDBNext[0].getCount() == 100){
               System.out.println("dataDBNext ==== ");
           }
        }
        System.out.println(dataDBNext[0].getCount());
    }
}
