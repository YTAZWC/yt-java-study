package top.ytazwc.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title GenericVarargsMethodDemo
 * @date 2024/5/22 8:43
 * @package top.ytazwc.generics
 * @description 泛型方法中也可以使用可变参数列表
 */
public class GenericVarargsMethodDemo {


    @SafeVarargs  // 该注解用于告诉编译器 已经注意到类型安全的问题(但是并非真的安全,而需要自己注意)
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        Collections.addAll(result, args);
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        System.out.println(ls);
    }

}
