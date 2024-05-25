package top.ytazwc.javacore;

import java.lang.reflect.Array;

/**
 * @author 花木凋零成兰
 * @title ReflectArrayDemo
 * @date 2024/5/25 14:50
 * @package top.ytazwc.javacore
 * @description 利用反射创建数组实例
 * 通过 Array.newInstance 创建数组的实例
 */
public class ReflectArrayDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls, 25);
        // 往数组里添加内容
        Array.set(array, 0, "Scala");
        Array.set(array, 1, "java");
        Array.set(array, 2, "Groovy");
        Array.set(array, 3, "Scala");
        Array.set(array, 4, "Clojure");
        // 获取某一项的内容
        System.out.println(Array.get(array, 3));



    }

}
