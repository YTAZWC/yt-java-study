package top.ytazwc.socket.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title Server5
 * @date 2024/6/5 23:04
 * @package top.ytazwc.socket.server
 * @description 网络聊天室服务端
 * 一个服务端支持多个客户端同时连接
 * 每个客户端都能不断读取用户键入的消息；发送给服务器并由服务器广播到所有连到服务器的客户端，实现群聊的功能
 */
public class Server5 {

    // 客户端列表
    public static List<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) {
        try (
            // 开启一个TCP服务 占用一个本地端口
            ServerSocket serverSocket = new ServerSocket(6666);
        ) {
            System.out.println("server start ...");
            // 服务端循环不断接受客户端的连接
            while (true) {
                Socket socket;
                try {
                    // 阻塞 等待客户端连接
                    socket = serverSocket.accept();
                    socketList.add(socket);
                    System.out.println("客户端" + socket.getRemoteSocketAddress() + "上线了");
                    // 为每个客户端分配一个线程
                    // 因为在读取每个客户端发送的数据时 线程会被阻塞
                    // 为了处理多个客户端的通信 需要为每个客户端分配一个线程
                    Thread workThread = new Thread(new ServerHandler(socket));
                    workThread.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ServerHandler implements Runnable {

    private Socket socket;
    private BufferedReader socketBufferedReader;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            // 从 Socket 中获得输入输出流 接收和发送数据
            String msg;
            while ((msg = readMsgFromClient()) != null) {
                System.out.println("收到来自客户端" + socket.getRemoteSocketAddress() + "的消息: " + msg);
                String message = "客户端【" + socket.getRemoteSocketAddress() + "】说: " + msg;
                for (Socket so : Server5.socketList) {
                    PrintWriter socketPrintWriter = new PrintWriter(so.getOutputStream(), true);
                    socketPrintWriter.println(message);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
    }

    // 读取从客户端发送的消息
    private String readMsgFromClient() {
        try {
            // 此处读取数据时 线程会被阻塞
            return socketBufferedReader.readLine();
        } catch (IOException e) {
            // 如果捕获到异常 则删除客户端对应的socket
            Server5.socketList.remove(socket);
            throw new RuntimeException(e);
        }
    }

}
