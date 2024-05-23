package top.ytazwc.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title GenericsLowerBoundedWildcardDemo
 * @date 2024/5/22 14:59
 * @package top.ytazwc.generics
 * @description 下界通配符
 * 下界通配符 将未知类型限制为该类型的特定类型或超类类型
 * 且 上界通配符和下界通配符不能同时使用
 */
public class GenericsLowerBoundedWildcardDemo {

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        addNumbers(list);
        System.out.println(Arrays.deepToString(list.toArray()));

    }

}
