package top.ytazwc.socket.io.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author 花木凋零成兰
 * @title Client2
 * @date 2024/6/5 20:46
 * @package top.ytazwc.socket.client
 * @description 客户端能够不断地接收用户输入，多次与服务端通信
 */
public class Client2 {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket();
        ) {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
            socket.connect(address, 2000);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String clientMsg;
            System.out.println("请输入消息: ");
            while ((clientMsg = bufferedReader.readLine()) != null) {
                outputStream.write(clientMsg.getBytes(StandardCharsets.UTF_8));
                byte[] buf = new byte[1024];
                int readLen = inputStream.read(buf);
                String msgFromServer = new String(buf, 0, readLen);
                System.out.println("来自服务端的消息: " + msgFromServer);
                System.out.println("请输入消息: ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
