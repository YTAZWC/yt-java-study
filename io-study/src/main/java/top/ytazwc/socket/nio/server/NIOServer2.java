package top.ytazwc.socket.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yt
 * 2024/6/6
 * 版本2：使用NIO非阻塞模式
 * 在非阻塞模式下，服务端只需启动一个主线程，能同时完成3件事：
 * - 接受客户端的连接
 * - 接受客户端发送的数据
 * - 向客户端发送响应数据
 */
public class NIOServer2 {
    private int port = 7777;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Charset charset = StandardCharsets.UTF_8;

    public NIOServer2() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        // 设置地址重用
        serverSocketChannel.socket().setReuseAddress(true);
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("server started ...");
    }

    public static void main(String[] args) {
        try {
            new NIOServer2().service();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void service() {
        try {
            // 注册选择器 且监听通道上的 接受连接请求事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 当监听到对应的事件时
            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 通过迭代器进行遍历
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = null;
                    // 处理每个 SelectionKey 的代码放入 try/catch 块中
                    // 出现异常 则使其失效并关闭对应的 Channel
                    try {
                        key = iterator.next();
                        if (key.isAcceptable()) {
                            doAccept(key);
                        }
                        if (key.isWritable()) {
                            sendMsg(key);
                        }
                        if (key.isReadable()) {
                            receiveMsg(key);
                        }
                        // 删除处理过的 SelectionKey
                        iterator.remove();
                    } catch (Exception e) {
                        try {
                            // 发生异常时 使 SelectionKey 失效
                            if (key != null) {
                                key.cancel();
                                // 关闭 SelectionKey 关联的 SocketChannel
                                key.channel().close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace(System.err);
                        }
                        e.printStackTrace(System.err);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doAccept(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = ssc.accept();
            System.out.println("接收到来自客户端的连接,来自" + socketChannel.socket().getRemoteSocketAddress());
            // 设置为非阻塞模式
            socketChannel.configureBlocking(false);
            // 创建用于接收客户端数据的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 向 Selector 注册读、写就绪事件 再关联buffer附件
            socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMsg(SelectionKey key) {
        // 将key的附件转化为 ByteBuffer 类型并赋值给变量buffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            // 为读取数据做准备
            buffer.flip();
            // 解码
            String data = decode(buffer);
            // 凑满一行数据再回复客户端
            if (!data.contains("\r\n")) {
                return ;
            }
            // 读取一行数据
            String recData = data.substring(0, data.indexOf("\n") + 1);
            System.out.print("客户端[" + socketChannel.socket().getInetAddress() + ":" + socketChannel.socket().getPort() + "]说: " + recData);
            ByteBuffer outputBuffer = encode(genResponse(recData));
            while (outputBuffer.hasRemaining()) {
                // 响应消息写入 socketChannel
                socketChannel.write(outputBuffer);
            }
            ByteBuffer temp = encode(recData);
            // 设置位置为 已处理数据编码后的长度
            buffer.position(temp.limit());
            // 删除buffer中已经处理过的数据
            buffer.compact();
            if ("byte\r\n".equals(recData)) {
                key.cancel();
                key.channel().close();
                System.out.println("关闭与客户端" + socketChannel.socket().getRemoteSocketAddress() + "的连接");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void receiveMsg(SelectionKey key) {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            // 创建一个 ByteBuffer 存放读取到的数据
            ByteBuffer readBuffer = ByteBuffer.allocate(64);
            socketChannel.read(readBuffer);
            readBuffer.flip();
            // 设置Buffer限制为它的容量，Buffer的限制是指在读取或写入操作中Buffer允许访问的最大位置
            // 通过将限制设置为容量，可以确保在读取或写入操作中不会出现越界情况
            buffer.limit(buffer.capacity());
            // 把 readBuffer 中的数据拷贝到 buffer 中；假设buffer的容量足够大，不会出现溢出的情况
            // 在非阻塞模式下，socketChannel.read(readBuffer)方法一次读入多少字节的数据是不确定的，无法保证一次读入的是一整行字符串数据
            // 因此需要将每次读取到的数据放到 buffer 中 ，当凑到一行数据时再返回给客户端
            buffer.put(readBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 编码 转化为字节
    private ByteBuffer encode(String msg) {
        return charset.encode(msg);
    }


    // 解码 转化为字符串
    private String decode(ByteBuffer buffer) {
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

    private String genResponse(String msg) {
        return "服务器收到了您的消息：" + msg;
    }

}
