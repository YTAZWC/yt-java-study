package top.ytazwc.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title GenericsErasureTypeDemo
 * @date 2024/5/22 9:00
 * @package top.ytazwc.generics
 * @description 类型擦除示例
 */
public class GenericsErasureTypeDemo {
    public static void main(String[] args) {

        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        System.out.println(list1.getClass());
        System.out.println(list2.getClass());

    }
}
