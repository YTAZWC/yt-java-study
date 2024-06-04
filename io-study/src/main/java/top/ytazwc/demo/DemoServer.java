package top.ytazwc.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 花木凋零成兰
 * @title DemoServer
 * @date 2024/6/4 22:57
 * @package top.ytazwc.demo
 * @description 设想 实现一个服务器应用 只简单能够同时处理多个客户端请求
 * 使用java.io和java.net的同步、阻塞式API 可以简单实现
 */
public class DemoServer extends Thread {
    private ServerSocket serverSocket;
    // 获取本地端口
    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            // 端口设置为0 表示自动绑定一个空闲端口
            serverSocket = new ServerSocket(0);
            while (true) {
                // 阻塞等待客户端连接
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DemoServer server = new DemoServer();
        server.start();
        // 模拟简单客户端 只进行连接、读取、打印
        try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 简化实现 不做读取 直接发送字符串
    class RequestHandler extends Thread {
        private Socket socket;

        RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                out.println("Hello world!");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
