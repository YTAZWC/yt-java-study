package top.ytazwc.socket.nio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author yt
 * 2024/6/6
 * 实现客户端服务端的网络通讯，客户端每发一条消息，服务端加前缀加以区分
 * 版本一：使用NIO阻塞模式，并配以线程池方式
 */
public class NIOClient1 {

    private SocketChannel socketChannel;

    public NIOClient1() throws IOException {
        socketChannel = SocketChannel.open();
        // 获取本地主机的IP地址
        InetAddress localhost = InetAddress.getLocalHost();
        // 创建套接字地址对象
        InetSocketAddress socketAddress = new InetSocketAddress(localhost, 7777);
        // 采用阻塞模式连接服务器
        socketChannel.connect(socketAddress);
        System.out.println("与服务端连接成功!");
    }

    public void chat() {
        Socket socket = socketChannel.socket();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while ((msg = input.readLine()) != null) {
                writer.println(msg);
                System.out.println("【服务器】说:" + reader.readLine());
                // 如果输入 bye 则终止聊天
                if ("bye".equals(msg)) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        new NIOClient1().chat();
    }

}
