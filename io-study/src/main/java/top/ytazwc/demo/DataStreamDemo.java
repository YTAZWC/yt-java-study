package top.ytazwc.demo;

import java.io.*;
import java.nio.file.Files;

/**
 * @author yt
 * 2024/6/3
 */
public class DataStreamDemo {

    public static final String FILEPATH = "E:/workspace/yt-java-study/io-study/data/temp.log";

    public static void main(String[] args) throws IOException {
        write(FILEPATH);
        read(FILEPATH);
    }

    private static void write(String filepath) throws IOException {
        // 1. 实例化 File 类
        File file = new File(filepath);
        // 2. 将 File 对象绑定到流对象
        DataOutputStream dos = new DataOutputStream(Files.newOutputStream(file.toPath()));
        // 3. 进行写操作
        String[] names = {"衬衣", "手套", "围巾"};
        float[] prices = {98.3f, 30.3f, 50.5f};
        int[] nums = {3, 2, 1};
        for (int i = 0; i < names.length; i++) {
            dos.writeChars(names[i]);
            dos.writeChars("\t");
            dos.writeFloat(prices[i]);
            dos.writeChars("\t");
            dos.writeInt(nums[i]);
            dos.writeChars("\n");
        }
        // 4. 关闭流
        dos.close();
    }

    private static void read(String filepath) throws IOException {
        // 1. 实例化 File 类实例
        File file = new File(filepath);
        // 2. 把 File 对象绑定到流对象上
        DataInputStream dis = new DataInputStream(Files.newInputStream(file.toPath()));
        // 3. 进行读操作
        String name;
        float price;
        int num;
        // 接收商品名称
        char[] temp;
        int len = 0;
        // '\u0000'
        char c = 0;
        try {
            while (true) {
                // 开辟空间
                temp = new char[200];
                len = 0;
                while ((c = dis.readChar()) != '\t') {
                    // 接收内容
                    temp[len] = c;
                    len ++;
                }
                // 将字符数组变为String
                name = new String(temp, 0, len);
                // 读取价格
                price = dis.readFloat();
                // 读取 \t
                dis.readChar();
                // 读取 int 数量
                num = dis.readInt();
                dis.readChar();
                System.out.printf("名称: %s, 价格: %5.3f, 数量: %d\n", name, price, num);
            }
        } catch (EOFException e) {
            System.out.println("结束");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        // 4. 关闭流
        dis.close();
    }

}
