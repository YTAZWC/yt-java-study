package top.ytazwc.socket.io.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author 花木凋零成兰
 * @title Client5
 * @date 2024/6/5 22:52
 * @package top.ytazwc.socket.client
 * @description 网络聊天室客户端
 * 一个服务端支持多个客户端同时连接
 * 每个客户端都能不断读取用户键入的消息；发送给服务器并由服务器广播到所有连到服务器的客户端，实现群聊的功能
 */
public class Client5 {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket();
        ) {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
            socket.connect(address, 2000);
            // 因为客户端在读取服务端返回数据时 会发生阻塞
            // 所以单独启动一个线程用来从流中读取服务端的数据
            new Thread(new ClientHandler(socket)).start();
            PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedInputReader = new BufferedReader(new InputStreamReader(System.in));
            String clientMsg;
            System.out.println("请输入消息:");
            while ((clientMsg = bufferedInputReader.readLine()) != null) {
                socketPrintWriter.println(clientMsg);
                System.out.println("请输入消息:");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msgFromServer;
            while ((msgFromServer = socketBufferedReader.readLine()) != null) {
                System.out.println("收到来自服务端的消息: " + msgFromServer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
