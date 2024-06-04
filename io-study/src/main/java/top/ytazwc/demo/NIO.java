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
            // 获取选择器中已准备就绪的事件集合迭代器
            Iterator<SelectionKey> events = selector.selectedKeys().iterator();
            // 遍历就绪事件
            while (events.hasNext()) {
                // 获取事件
                SelectionKey event = events.next();
                // 不同类型事件 做不同处理
                if (event.isAcceptable()) {
                    // 如果是可接受事件 则接受客户端连接
                    SocketChannel sc = ssc.accept();
                    // 设置为非阻塞模式
                    sc.configureBlocking(false);
                    // 设置感兴趣事件类型为读取
                    sc.register(selector, SelectionKey.OP_READ);
                    // 客户端连接数增加
                    ++ connectionNum;
                } else if (event.isReadable()) {
                    // 若是可读事件 则读取客户端发送的数据
                    try (SocketChannel sc = (SocketChannel) event.channel()) {
                        // 创建一个 1024 个字节大小的缓冲区
                        // 当连接中的数据 大于 1024 字节时 会触发以下两种事件的通知机制：
                        // 1. 水平触发(level-triggered) 称作LT模式 只要缓冲区有数据 事件就会一直发生
                        // 2. 边缘触发(edge-triggered) 称作ET模式 缓冲区有数据 仅会触发一次 事件想要再次触发 必须先将fd中的数据读完
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        // 读取数据到缓冲区
                        int size = sc.read(buffer);
                        // 缓冲区数据转化为 字符串
                        String result = new String(buffer.array()).trim();
                        // 准备响应客户端的 响应数据
                        ByteBuffer wrap = ByteBuffer.wrap(("PONG: " + result).getBytes());
                        // 发送响应
                        sc.write(wrap);
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                } else if (event.isWritable()) {
                    // 如果是可写事件 则 ...
                    // 当底层缓冲区有空闲 事件就会一直发生 浪费占用 CPU 资源
                    // 所以一般不注册 OP_WRITE 事件
                    try (SocketChannel sc = (SocketChannel) event.channel()) {
                    }
                }
                // 从迭代器中溢出当前事件 表示已处理
                events.remove();
            }
        }
        // 关闭线程池
        service.shutdown();
        ssc.close();
    }
}
