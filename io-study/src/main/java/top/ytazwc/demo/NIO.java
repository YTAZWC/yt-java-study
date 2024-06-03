package top.ytazwc.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 花木凋零成兰
 * @title NIO
 * @date 2024/6/3 23:09
 * @package top.ytazwc.demo
 * @description NIO服务器示例
 */
public class NIO {
    // 控制服务的停止
    static boolean stop = false;

    public static void main(String[] args) throws IOException {
        // 记录已连接的数量
        int connectionNum = 0;
        // 服务端口
        int port = 8888;
        // 创建缓存线程池 用于处理客户端连接
        ExecutorService service = Executors.newCachedThreadPool();
        // 监听客户端连接
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置为非阻塞模式
        ssc.configureBlocking(false);
        // 绑定服务器地址和端口
        ssc.socket().bind(new InetSocketAddress("localhost", port));
        // 创建 selector 监听多个通道的 IO 事件
        Selector selector = Selector.open();
        /*
         * 服务端注册 事件选择器 监听 OP_ACCEPT 事件
         * 一共有四种事件类型：
         * - 新连接事件(OP_ACCEPT)
         * - 连接就绪事件(OP_CONNECT)
         * - 读就绪事件(OP_READ)
         * - 写就绪事件(OP_WRITE)
         */
        ssc.register(selector, ssc.validOps());
        while (!stop) {
            if (10 == connectionNum) {
                stop = true;
            }
            // 使用 select 函数 阻塞主线程 即操作系统不再分配CPU时间片到当前线程中
            // 所以select函数几乎不占用任何系统资源
            int num = selector.select();
            if (num == 0) {
                continue;
            }
            Iterator<SelectionKey> events = selector.selectedKeys().iterator();
            while (events.hasNext()) {
                SelectionKey event = events.next();
                if (event.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    ++connectionNum;
                } else if (event.isReadable()) {
                    try (SocketChannel sc = (SocketChannel) event.channel()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int size = sc.read(buffer);
                        String result = new String(buffer.array()).trim();
                        ByteBuffer wrap = ByteBuffer.wrap(("PONG: " + result).getBytes());
                        sc.write(wrap);
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                } else if (event.isWritable()) {
                    try (SocketChannel sc = (SocketChannel) event.channel()) {
                    }
                }
                // 从迭代器中溢出当前事件 表示已处理
                events.remove();
            }
        }
        service.shutdown();
        ssc.close();
    }
}
