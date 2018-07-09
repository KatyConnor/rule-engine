package hx.com.example.rule;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author mingliang
 * @Date 2017-12-24 19:19
 */
public class DstService {

    public static void main(String[] args) {
        try {
            // 启动监听端口 8001
            ServerSocket ss = new ServerSocket(8001);
            boolean bRunning = true;
            while (bRunning) {
                // 接收请求
                Socket s = ss.accept();
                System.out.println("启动！");
                // 将请求指定一个线程去执行
                new Thread(new ClientThread(s)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
