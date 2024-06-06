package top.ytazwc.socket.nio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yt
 * 2024/6/6
 * 实现客户端服务端的网络通讯，客户端每发一条消息，服务端加前缀加以区分
 * 版本一：使用NIO阻塞模式，并配以线程池方式
 */
public class NIOServer1 {

    private int port = 7777;
    private ServerSocketChannel serverSocketChannel;
    // 线程池
    private ExecutorService executorService;

    public NIOServer1() throws IOException {
        // 创建一个固定大小的线程池
        // 线程池的大小是可用处理器核心的4倍
        // Runtime.getRuntime().availableProcessors() 用于获取系统可用处理器核心数
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*4);
        serverSocketChannel = ServerSocketChannel.open();
        // 允许地址重用 关闭服务端程序之后 立即再次启动该程序可以顺利绑定相同的端口
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("server started ...");
    }

    /**
     * 服务端核心业务处理方法，负责接收并处理客户端的连接请求。
     * 采用无限循环的方式，确保服务端可以持续监听并响应客户端的连接。
     * 当有新的客户端连接请求时，会创建一个新的线程来处理该连接，以实现并发处理多个客户端连接。
     * @throws RuntimeException 如果接受客户端连接时发生IO异常，则抛出运行时异常。
     */
    private void service() {
        while (true) {
            SocketChannel socketChannel;
            try {
                // 阻塞等待客户端连接
                socketChannel = serverSocketChannel.accept();
                // 提交处理任务到线程池，由线程池中的线程负责具体处理客户端连接。
                executorService.execute(new NIOHandler1(socketChannel));
            } catch (IOException e) {
                // 接收客户端连接发生异常时，抛出运行时异常。
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NIOServer1().service();
    }

}

class NIOHandler1 implements Runnable {

    private SocketChannel socketChannel;

    public NIOHandler1(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        Socket socket = socketChannel.socket();
        System.out.println("接收到客户端的连接,来自" + socket.getRemoteSocketAddress());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String msg;
            while ((msg = reader.readLine()) != null) {
                System.out.println("客户端【" + socket.getInetAddress() + ":" + socket.getPort() + "】说：" + msg);
                writer.println(getResponse(msg));
                if ("bye".equals(msg)) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(String msg) {
        return "服务端收到了您的消息：" + msg;
    }

}
