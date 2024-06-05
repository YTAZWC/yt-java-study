package top.ytazwc.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yt
 * 2024/6/5
 * NIO案例-文件复制
 */
public class FileCopy01 {
    public static void main(String[] args) {
        try (
            FileInputStream fis = new FileInputStream("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01.txt");
            FileOutputStream fos = new FileOutputStream("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01_copy01.txt");
            // 获取channel
            FileChannel in = fis.getChannel();
            FileChannel out = fos.getChannel();
        ) {
            // 创建直接缓冲区，进一步提高IO性能，但是分配缓冲区的系统开销较大，只适合缓冲区较大且需要长期驻留的情况
            ByteBuffer buffer = ByteBuffer.allocate(4);
            // 多次重复“取水”方式
            while (in.read(buffer) != -1) {
                // 为从Buffer中取出数据做好准备
                buffer.flip();
                out.write(buffer);
                // 为再次向Buffer写入数据做好准备
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
