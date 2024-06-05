package top.ytazwc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yt
 * 2024/6/5
 * NIO示例-文件复制-映射方式
 */
public class FileCopy02 {
    public static void main(String[] args) {
        File f = new File("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01.txt");
        try (
            FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01_copy02.txt");
            FileChannel in = fis.getChannel();
            FileChannel out = fos.getChannel();
        ) {
            // 将 FileChannel 的全部数据映射到 ByteBuffer 中
            MappedByteBuffer mappedByteBuffer = in.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            out.write(mappedByteBuffer);
            mappedByteBuffer.clear();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
