package top.ytazwc.javacore;

import java.io.PrintStream;

/**
 * @author 花木凋零成兰
 * @title ReflectClassDemo02
 * @date 2024/5/25 12:51
 * @package top.ytazwc.javacore
 * @description 直接用类名 + .class 获取 Class 对象
 */
public class ReflectClassDemo02 {
    public static void main(String[] args) {
        boolean b;
        Class<?> c1 = boolean.class;
        System.out.println(c1.getCanonicalName());

        Class<PrintStream> c2 = PrintStream.class;
        System.out.println(c2.getCanonicalName());

        Class<?> c3 = int[][][].class;
        System.out.println(c3.getCanonicalName());

    }
}
