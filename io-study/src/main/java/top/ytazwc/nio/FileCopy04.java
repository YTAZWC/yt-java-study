package top.ytazwc.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author yt
 * 2024/6/5
 * NIO示例-文件复制-零拷贝方式
 * transerTo方式
 */
public class FileCopy04 {
    public static void main(String[] args) {
        try (
            FileInputStream fis = new FileInputStream("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01.txt");
            FileOutputStream fos = new FileOutputStream("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01_copy04.txt");
            FileChannel srcChannel = fis.getChannel();
            FileChannel destChannel = fos.getChannel();
        ) {
            long size = srcChannel.size();
            long position = 0;
            while (size > 0) {
                long count = srcChannel.transferTo(position, srcChannel.size(), destChannel);
                position += count;
                size -= count;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
