package top.ytazwc.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author yt
 * 2024/6/5
 * 简单客户端/服务端一次性简单通信 服务端
 */
public class Server1 {
    public static void main(String[] args) {

        try (
            // 开启一个 TCP 服务端 占用一个本地端口
            ServerSocket serverSocket = new ServerSocket(6666);
        ){
            // 服务端循环不断接受客户端的连接
            while (true) {
                Socket socket = null;
                // 与单个客户端通信的代码放在一个 try 代码块中，单个客户端发生异常(断开)时不影响服务端正常工作
                try {
                    System.out.println("server start ...");
                    // 阻塞 直到有客户端连接
                    socket = serverSocket.accept();
                    System.out.println("客户端 " + socket.getRemoteSocketAddress() + " 上线了");
                    // 从 Socket 中获取输入输出流 接收和发送数据
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) != -1) {
                        String msg = new String(buf, 0, len);
                        System.out.println("来自客户端的消息: " + msg);
                        String responseMsg = "服务端收到了来自您的消息[" + msg + "], 并且探测到您的 IP 是: " + socket.getRemoteSocketAddress();
                        // 向客户端写回消息
                        outputStream.write(responseMsg.getBytes(StandardCharsets.UTF_8));
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
