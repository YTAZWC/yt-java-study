package top.ytazwc.javacore;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 花木凋零成兰
 * @title ReflectClassDemo03
 * @date 2024/5/25 13:32
 * @package top.ytazwc.javacore
 * @description Object 的 getClass 方法获取 Class 对象
 */
public class ReflectClassDemo03 {

    enum E {A, B}

    public static void main(String[] args) {

        Class<?> c1 = "foo".getClass();
        System.out.println(c1.getCanonicalName());

        Class<?> c2 = ReflectClassDemo03.E.A.getClass();
        System.out.println(c2.getCanonicalName());

        byte[] bytes = new byte[1024];
        Class<?> c3 = bytes.getClass();
        System.out.println(c3.getCanonicalName());

        Set<String> set = new HashSet<>();
        Class<?> c4 = set.getClass();
        System.out.println(c4.getCanonicalName());

    }

}
