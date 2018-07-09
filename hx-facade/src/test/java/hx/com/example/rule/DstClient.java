package hx.com.example.rule;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author mingliang
 * @Date 2017-12-24 19:23
 */
public class DstClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8001);
            // 开启保持活动状态的套接字
            socket.setKeepAlive(true);
            // 设置读取超时时间
            socket.setSoTimeout(30 * 1000);
            OutputStream ops = socket.getOutputStream();
            String mess = "你好，我是崔素强！";
            ops.write(mess.getBytes());
            InputStream ips = socket.getInputStream();
            byte[] rebyte = StreamTool.readStream(ips);
            String remess = new String(rebyte);
            System.out.println("收到主机消息：" + remess);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
