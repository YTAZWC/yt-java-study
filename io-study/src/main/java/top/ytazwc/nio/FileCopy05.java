package top.ytazwc.nio;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yt
 * 2024/6/5
 * NIO模型-使用新的API实现文件复制
 */
public class FileCopy05 {
    public static void main(String[] args) {
        try (
                OutputStream fos = Files.newOutputStream(Paths.get("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01_copy05.txt"));
                ) {
            Files.copy(Paths.get("E:\\workspace\\yt-java-study\\io-study\\data\\nio\\file01.txt"), fos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
