package top.ytazwc.demo;

import java.io.*;

/**
 * @author yt
 * 2024/6/3
 */
public class FileStreamDemo {
    private static final String FILEPATH = "E:/workspace/yt-java-study/io-study/data/temp.log";

    public static void main(String[] args) throws IOException {
        write(FILEPATH);
        read(FILEPATH);
    }

    // 写入文件
    public static void write(String filepath) throws IOException {
        // 1、创建File类
        File f = new File(filepath);
        // 2、文件输出流关联到 File 类
        // 子类实例化父类对象
        // 实例化时 默认为覆盖源文件内容方式；添加ture参数 则变为对原文件追加内容
        OutputStream out = new FileOutputStream(f);
//        OutputStream out = new FileOutputStream(f, true);
        // 3、写操作
        String str = "Hello World!\n";
        byte[] bytes = str.getBytes();
        out.write(bytes);

        // 4、关闭流
        out.close();
    }

    // 读文件
    public static void read(String filepath) throws IOException {
        // 1、根据文件路径绑定文件
        File f = new File(filepath);
        // 2、输入流绑定 File 对象
        InputStream in = new FileInputStream(f);
        // 3、进行读操作
        byte[] bytes = new byte[(int) f.length()];
        int len = in.read(bytes);
        System.out.println("读入数据的长度: " + len);
        // 关闭输入流
        in.close();

        System.out.println("内容为: \n" + new String(bytes));
    }

}
