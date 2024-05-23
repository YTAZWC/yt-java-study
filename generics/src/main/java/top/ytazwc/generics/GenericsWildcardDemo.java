package top.ytazwc.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title GenericsWildcardDemo
 * @date 2024/5/22 15:17
 * @package top.ytazwc.generics
 * @description 使用通配符来实现向上转型
 */
public class GenericsWildcardDemo {

    public static void main(String[] args) {

        List<Integer> intList = new ArrayList<>();
        // Error
//        List<Number> numList = intList;

        List<? extends Integer> intList2 = new ArrayList<>();
        List<? extends Number> numList2 = intList2;

    }

}
