package top.ytazwc.socket.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 花木凋零成兰
 * @title NIOServer3
 * @date 2024/6/6 22:44
 * @package top.ytazwc.socket.nio.server
 * @description 基于 NIO 重写网络聊天室的案例
 */
public class NIOServer3 {
    private int port = 6666;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Charset charset = StandardCharsets.UTF_8;

    public NIOServer3() throws IOException {
        // 实例化选择器
        selector = Selector.open();
        // 实例化服务套接字通道
        serverSocketChannel = ServerSocketChannel.open();
        // 设置地址重用
        serverSocketChannel.socket().setReuseAddress(true);
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
    }

    private void service() {
        try {
            // 服务通道注册选择器 以及监听事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select() > 0) {
                // 获取SelectKey集合，并进行迭代
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        // 监听到客户端连接事件
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ssc.accept();
                        System.out.println("接收到客户端的链接,来自" + socketChannel.socket().getRemoteSocketAddress());
                        // 设置为非阻塞模式
                        socketChannel.configureBlocking(false);
                        // 重新注册事件为 读就绪
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        // 监听到 可读事件
                        // 转化为客户端套接字通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        // 创建缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        // 接收到的客户端的消息
                        StringBuilder msg = new StringBuilder();
                        try {
                            while (sc.read(buffer) > 0) {
                                // 为数据进入缓存区做准备
                                buffer.flip();
                                // 解码拼接到消息中
                                msg.append(charset.decode(buffer));
                            }
                            System.out.println("客户端【" + sc.getRemoteAddress() + "】说：" + msg);
                        } catch (IOException e) {
                            e.printStackTrace(System.err);
                            try {
                                // 对某个Client对应读写发生异常时，使SelectionKey无效
                                // 且Selector不再监控这个SelectionKey感兴趣事件
                                key.cancel();
                                System.out.println("客户端【" + ((SocketChannel)key.channel()).socket().getRemoteSocketAddress() + "】下线了");
                                key.channel().close();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        String msgg = msg.toString();
                        if (!msgg.isEmpty()) {
                            for (SelectionKey selectionKey : selector.keys()) {
                                Channel channel = selectionKey.channel();
                                // 遍历Selector中的所有注册的Channel，如果是客户端的SocketChannel，则群发消息，并排除自己
                                if (channel instanceof SocketChannel && channel != sc) {
                                    SocketChannel socketChannel = (SocketChannel) channel;
                                    socketChannel.write(charset.encode("用户【" + sc.getRemoteAddress() + "】说：" + msg));
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        new NIOServer3().service();
    }

}
