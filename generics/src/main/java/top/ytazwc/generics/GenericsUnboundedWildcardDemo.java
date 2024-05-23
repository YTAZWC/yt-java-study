package top.ytazwc.generics;

import java.util.Arrays;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title GenericsUnboundedWildcardDemo
 * @date 2024/5/22 15:07
 * @package top.ytazwc.generics
 * @description TODO
 */
public class GenericsUnboundedWildcardDemo {

    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> listInt = Arrays.asList(1,2, 3);
        List<String> listStr = Arrays.asList("one", "two", "three");
        printList(listInt);
        printList(listStr);
    }

}
