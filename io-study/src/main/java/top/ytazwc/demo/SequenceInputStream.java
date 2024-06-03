package top.ytazwc.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yt
 * 2024/6/3
 */
public class SequenceInputStream {
    public static void main(String[] args) throws IOException {
        InputStream in1 = Files.newInputStream(Paths.get("E://workspace/yt-java-study/io-study/data/temp1.log"));
        InputStream in2 = Files.newInputStream(Paths.get("E://workspace/yt-java-study/io-study/data/temp2.log"));
        java.io.SequenceInputStream sis = new java.io.SequenceInputStream(in1, in2);
        // 接收内容
        int temp;
        OutputStream out = Files.newOutputStream(Paths.get("E://workspace/yt-java-study/io-study/data/temp3.log"));
        // 循环输出
        while ((temp = sis.read()) != -1) {
            // 保存内容
            out.write(temp);
        }
        // 关闭合并流
        sis.close();
        in2.close();
        in1.close();
        out.close();
    }
}
