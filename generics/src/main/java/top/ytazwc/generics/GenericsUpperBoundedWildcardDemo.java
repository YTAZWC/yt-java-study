package top.ytazwc.generics;

import java.util.Arrays;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title GenericsUpperBoundedWildcardDemo
 * @date 2024/5/22 9:58
 * @package top.ytazwc.generics
 * @description 上界通配符 用来限制类型参数范围
 * 语法形式为：<? extends Number>
 */
public class GenericsUpperBoundedWildcardDemo {

    public static double sumAll(List<? extends Number> list) {
        return list.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println("sum = " +sumAll(list));
    }

}
