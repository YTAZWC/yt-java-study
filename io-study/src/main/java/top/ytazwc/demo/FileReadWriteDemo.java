package top.ytazwc.demo;

import java.io.*;

/**
 * @author yt
 * 2024/6/3
 */
public class FileReadWriteDemo {
    private static final String FILEPATH = "E:/workspace/yt-java-study/io-study/data/temp.log";

    public static void main(String[] args) throws IOException {
        write(FILEPATH);
        System.out.println("内容为: " + new String(read(FILEPATH)));
    }

    private static void write(String filepath) throws IOException {
        // 1. 实例File类
        File file = new File(filepath);
        // 2. 把 File 对象绑定到流对象上
        Writer out = new FileWriter(file);
        // 3. 进行写操作
        String str = "Hello World!!!\r\n";
        out.write(str);
        // 关闭流
        // 字符流操作时使用了缓冲区 并在关闭字符流时 会强制将缓冲区的内容输出
        // 如果不关闭流 则缓冲区的内容时无法输出的
        // 若想在不关闭流时 将缓冲区内容输出 可使用 flush 强制清空缓冲区
        out.flush();
        out.close();
    }

    private static char[] read(String filepath) throws IOException {
        // 1. 实例化 File
        File file = new File(filepath);
        // 2. 将 File 对象绑定流对象上
        Reader input = new FileReader(file);
        // 3. 进行读操作
        // 接收每一个内容
        int temp = 0;
        int len = 0;
        char[] c = new char[(int)file.length()];
        while ((temp = input.read()) != -1) {
            // 非-1则说明还有内容 可继续读取
            c[len] = (char) temp;
            len ++;
        }
        System.out.println("文件字符数为: " + len);
        // 4. 关闭流
        input.close();
        return c;
    }
}
