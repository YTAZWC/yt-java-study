package top.ytazwc.socket.io.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author 花木凋零成兰
 * @title Client3
 * @date 2024/6/5 21:58
 * @package top.ytazwc.socket.client
 * @description 字符流包装
 * 使用字符流包装 向服务端发送消息和接受服务端的消息
 */
public class Client3 {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket();
        ) {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
            socket.connect(address, 2000);
            PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String clientMsg;
            System.out.println("请输入消息: ");
            while ((clientMsg = bufferedReader.readLine()) != null) {
                socketPrintWriter.println(clientMsg);
                String msgFromServer = socketBufferedReader.readLine();
                System.out.println("来自服务端的消息: " + msgFromServer);
                System.out.println("请输入消息: ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
