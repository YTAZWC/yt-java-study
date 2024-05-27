package top.ytazwc.javacore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title SafeVarargsAnnotationDemo
 * @date 2024/5/27 9:52
 * @package top.ytazwc.javacore
 * @description @SafeVarargs 的作用是：告诉编译器，在可变长参数中的泛型是类型安全的
 * 可变长参数是使用数组存储的，而数组和泛型不能很好的混合使用
 * 因为数组元素的数据类型在编译和运行时都是确定的，而泛型的数据类型只有在运行时才能确定下来
 * 因此，编译器在编译阶段无法确认数据类型是否匹配，因此会给出警告信息
 * 切如果泛型的真实数据类型无法和参数数组的类型匹配，会导致 ClassCastException 异常
 * @SafeVarargs注解使用范围：
 *  1、可以用于构造方法
 *  2、可以用于 static 和 final 方法
 */
public class SafeVarargsAnnotationDemo {
    //@SafeVarargs
    static void wrongMethod(List<String>... stringLists) {
        Object[] array = stringLists;
        List<Integer> tmpList = Arrays.asList(42);
        // 语法错误 编译不告警
        array[0] = tmpList;

        List<String> stringList = stringLists[0];
        // System.out.println(stringList);
        // 运行时报ClassCastException
        // String s = stringList.get(0);
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        wrongMethod(list1, list2);
    }
}
