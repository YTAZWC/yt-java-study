package top.ytazwc.socket.nio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
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
public class NIOClient2 {

    // 从服务器接收到的数据放在recvBuf中
    private ByteBuffer recvBuf = ByteBuffer.allocate(1024);
    // 将用户从控制台输入的数据存放到sendBuf中，并将sendBuf中的发送给服务器
    private ByteBuffer sendBuf = ByteBuffer.allocate(1024);
    // 统一字符编码
    private Charset charset = StandardCharsets.UTF_8;
    private SocketChannel socketChannel;
    private Selector selector;

    public NIOClient2() throws IOException {
        socketChannel = SocketChannel.open();
        InetAddress localhost = InetAddress.getLocalHost();
        InetSocketAddress socketAddress = new InetSocketAddress(localhost, 7777);
        // 采用阻塞模式连接服务器
        socketChannel.connect(socketAddress);
        // 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        System.out.println("与服务端连接成功!");
        selector = Selector.open();
    }

    public static void main(String[] args) throws IOException {
        NIOClient2 nioClient2 = new NIOClient2();
        Thread inputThread = new Thread() {
            @Override
            public void run() {
                nioClient2.receiveInput();
            }
        };
        inputThread.start();
        nioClient2.chat();
    }

    private void chat() {
        // 接收和发送数据
        try {
            // 注册选择器以及事件
            socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            while (selector.select() > 0) {

                // 获取迭代器
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = null;
                    try {
                        key = iterator.next();
                        if (key.isWritable()) {
                            sendMsg(key);
                        } else if (key.isReadable()) {
                            receiveMsg(key);
                        }
                        iterator.remove();
                    } catch (Exception e) {
                        try {
                            // 发生异常时 SelectionKey 失效 不再对其监控
                            if (key != null) {
                                // 关闭key关联的channel
                                key.channel().close();
                                key.cancel();
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        e.printStackTrace(System.err);
                    }
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 接收用户从控制台输入的数据
    private void receiveInput() {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while ((msg = inputReader.readLine()) != null) {
                // 因为输入的线程和发送数据给服务器的线程都会使用sendBuf 所以加了synchronize进行同步
                synchronized (sendBuf) {
                    sendBuf.put(encode(msg + "\r\n"));
                }
                // 用户输入bye 则终止聊天
                if ("bye".equals(msg)) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 发送sendBuf中的数据给服务端
    private void sendMsg(SelectionKey key) {
        // 发送sendMsg中的数据
        try (SocketChannel channel = (SocketChannel) key.channel()) {
            synchronized (sendBuf) {
                // 为取出数据做好准备
                sendBuf.flip();
                // 将 sendBuf 中的数据写入到 Channel 中去
                channel.write(sendBuf);
                // 删除已经发送的数据(通过压缩的方式)
                sendBuf.compact();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 接收来自服务端的数据 放到recvBuf中，满一行则输出，然后删除已输出数据
    private void receiveMsg(SelectionKey key) {
        try (SocketChannel channel = (SocketChannel) key.channel()) {
            channel.read(recvBuf);
            recvBuf.flip();
            // 解码
            String recvMsg = decode(recvBuf);
            if (!recvMsg.contains("\n")) {
                return ;
            }
            String recvMsgLine = recvMsg.substring(0, recvMsg.indexOf("\n") + 1);
            System.out.println("【服务器】说：" + recvMsgLine);
            if (recvMsgLine.contains("bye")) {
                key.cancel();
                socketChannel.close();
                System.out.println("与服务器断开连接");
                selector.close();
                System.exit(0);
            }
            ByteBuffer temp = encode(recvMsgLine);
            recvBuf.position(temp.limit());
            // 删除已输出数据
            recvBuf.compact();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ByteBuffer encode(String msg) {
        return charset.encode(msg);
    }

    private String decode(ByteBuffer buffer) {
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

}
