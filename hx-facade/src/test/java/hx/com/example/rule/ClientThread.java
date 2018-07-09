package hx.com.example.rule;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author mingliang
 * @Date 2017-12-24 18:38
 */
public class ClientThread implements Runnable {

    private Socket socket;

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("开始！");
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结束！");
    }

    private void test() throws Exception{
        InputStream in = socket.getInputStream(); //new FileInputStream("E:\\examplecode\\hx-rule\\hx-facade\\src\\main\\resources\\test.txt");
        int c = -1;
        int a = 'a';
        int count = 1;
        while ((a = in.read()) !=-1){
//            a = in.read();
            if (a == 1){
                break;
            }
//            System.out.println("c = "+(char) c);
            System.out.println("a = "+(char) a);
            System.out.println(count++);
        }
        System.out.println("dsdss");
    }
}
