package top.ytazwc.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author yt
 * 2024/6/4
 * AIO 将对事件的操作变为非阻塞的
 * 通过注册 CompletionHandler 回调函数进行事件处理
 * 且事件是隐藏的，等事件处理好后，会通过回调函数通知，再进行后续操作
 */
public class AIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8889;
        AsynchronousServerSocketChannel ssc = AsynchronousServerSocketChannel.open();
        // 绑定服务器地址和端口
        ssc.bind(new InetSocketAddress("localhost", port));
        ssc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            void job(final AsynchronousSocketChannel sc) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                sc.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        String str = new String(attachment.array()).trim();
                        ByteBuffer wrap = ByteBuffer.wrap(("PONG: " + str).getBytes());
                        sc.write(wrap, null, new CompletionHandler<Integer, Object>() {
                            @Override
                            public void completed(Integer result, Object attachment) {
                                job(sc);
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {
                                System.out.println("error");
                            }
                        });
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        System.out.println("error");
                    }
                });
            }

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                ssc.accept(null, this);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
                System.out.println("error");
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
