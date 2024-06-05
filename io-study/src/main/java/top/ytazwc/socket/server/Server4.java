package top.ytazwc.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 花木凋零成兰
 * @title Server4
 * @date 2024/6/5 22:06
 * @package top.ytazwc.socket.server
 * @description 实现多个客户端与服务器通信
 * 使用多线程的方式实现服务器同时响应多个客户端
 */
public class Server4 {
    public static void main(String[] args) {
        try (
            // 开启一个TCP服务，占用一个本地端口
            ServerSocket serverSocket = new ServerSocket(6666);
        ) {
            System.out.println("server start ...");
            // 服务端循环不断接受客户端连接
            while (true) {
                Socket socket;
                try {
                    // 阻塞
                    socket = serverSocket.accept();
                    System.out.println("客户端" + socket.getRemoteSocketAddress() + "上线了");
                    // 为每一个客户端分配一个线程
                    Thread workThread = new Thread(new Handler(socket));
                    workThread.start();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Handler implements Runnable {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 从Socket中获取输入输出流 接收和发送数据
            PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = socketBufferedReader.readLine()) != null) {
                System.out.println("来自客户端" + socket.getRemoteSocketAddress() + "的消息: " + msg);
                String responseMsg = "服务端收到了来自您的消息[" + msg + "],并探测到您的 IP 是: " + socket.getRemoteSocketAddress();
                socketPrintWriter.println(responseMsg);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            // 当与一个客户端通信结束 需要关闭对应的 socket
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
