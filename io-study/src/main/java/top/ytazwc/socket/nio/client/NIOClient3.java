package top.ytazwc.socket.nio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author 花木凋零成兰
 * @title NIOClient3
 * @date 2024/6/6 23:12
 * @package top.ytazwc.socket.nio.client
 * @description 基于 NIO 重写网络聊天室的案例
 */
public class NIOClient3 {
    // 用于接受服务端响应数据
    private ByteBuffer recvBuf = ByteBuffer.allocate(1024);
    // 用于接受用户从控制台输入的数据，以及向服务端发送的数据
    private ByteBuffer sendBuf = ByteBuffer.allocate(1024);
    // 编码
    private Charset charset = StandardCharsets.UTF_8;
    // 选择器
    private Selector selector;
    private SocketChannel socketChannel;

    public NIOClient3() throws IOException {
        selector = Selector.open();
        // 获取本地客户端地址
        InetAddress localhost = InetAddress.getLocalHost();
        InetSocketAddress socketAddress = new InetSocketAddress(localhost, 6666);
        // 采用阻塞模式连接服务器
        socketChannel = SocketChannel.open();
        socketChannel.connect(socketAddress);
        // 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        System.out.println("与服务端连接成功!");
    }

    public static void main(String[] args) throws IOException {
        NIOClient3 nioClient3 = new NIOClient3();
        Thread workThread = new Thread(nioClient3::sendInputMsg);
        workThread.start();
        nioClient3.receiveMsg();
    }

    // 接受来自服务端的消息
    private void receiveMsg() {
        // 注册选择器，并选择监听事件
        try {
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (selector.select() > 0) {
                // 通过迭代器来迭代SelectionKey
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        if (key.isReadable()) {
                            // 监听到 可读事件
                            SocketChannel sc = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            StringBuilder msg = new StringBuilder();
                            while (sc.read(buffer) > 0) {
                                buffer.flip();
                                msg.append(charset.decode(buffer));
                            }
                            System.out.println(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace(System.err);
                        // 发生异常时，使key失效，并不再监听key对应的事件
                        key.cancel();
                        key.channel().close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 接受键盘输入的消息并发送数据到服务器
    private void sendInputMsg() {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while ((msg = inputReader.readLine()) != null) {
                socketChannel.write(charset.encode(msg));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
