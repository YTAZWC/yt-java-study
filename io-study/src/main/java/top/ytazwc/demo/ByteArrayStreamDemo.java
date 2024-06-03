package top.ytazwc.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author yt
 * 2024/6/3
 */
public class ByteArrayStreamDemo {
    public static void main(String[] args) {
        // 定义一个字符串 全部由大写字母组成
        String str = "HELLO WORLD";
        ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 准备从内存ByteArrayInputStream中读取内容
        int temp;
        while ((temp = bis.read()) != -1) {
            // 读取的数字变为字符
            char c = (char)temp;
            // 将字符变为小写
            bos.write(Character.toLowerCase(c));
        }
        // 所有数据均在ByteArrayOutputStream中
        // 取出内容
        String newStr = bos.toString();
        try {
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        System.out.println(newStr);
    }
}
