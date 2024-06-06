package top.ytazwc.socket.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 花木凋零成兰
 * @title Server3
 * @date 2024/6/5 21:36
 * @package top.ytazwc.socket.client
 * @description 使用字符流包装
 * 之前的版本是按字节方式读取数据的，缓冲字节数组大小无法权衡，太小了不足以存放一行数据时，将会读取到不完整的数据
 * 产生乱码；使用字符流包装字节流，读取整行数据
 */
public class Server3 {
    public static void main(String[] args) {
        try (
            // 开启一个TCP客户端，占用一个本地端口
            ServerSocket serverSocket = new ServerSocket(6666);
        ) {
            // 服务端循环不断接受客户端的连接
            while (true) {
                Socket socket = null;
                // 与客户端通信的代码放在一个try块中 单个客户端发生异常或断开时不影响服务端正常工作
                try {
                    System.out.println("server start ...");
                    // 阻塞 直到有客户端连接
                    socket = serverSocket.accept();
                    System.out.println("客户端" + socket.getRemoteSocketAddress() + "上线了");
                    // 从 Socket 中获取输入输出流 接收和发送数据
                    PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = socketBufferedReader.readLine()) != null) {
                        System.out.println("来自客户端的消息: " + msg);
                        String responseMsg = "服务端收到了来自您的消息[" + msg + "], 并且探测到您的 IP 是: " + socket.getRemoteSocketAddress();
                        socketPrintWriter.println(responseMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                } finally {
                    // 当与一个客户端通信结束后 需要关闭对应的 socket
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace(System.err);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
