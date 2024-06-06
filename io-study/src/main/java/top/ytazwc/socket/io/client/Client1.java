package top.ytazwc.socket.io.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author yt
 * 2024/6/5
 * 简单客户端/服务端一次性简单通信 客户端
 */
public class Client1 {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket();
        ) {
            // 绑定服务端地址
            SocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
            // 向服务端发起连接 超过2000则连接超时
            socket.connect(address, 2000);
            OutputStream outputStream = socket.getOutputStream();
            // 客户端发送消息
            String clientMsg = "服务端你好! 我是客户端! 你的 IP 是: " + socket.getRemoteSocketAddress();
            outputStream.write(clientMsg.getBytes(StandardCharsets.UTF_8));

            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                String msgFromServer = new String(buf, 0, len);
                System.out.println("来自服务端的消息:" + msgFromServer);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
