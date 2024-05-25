package top.ytazwc.mooc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title MethodDemo4
 * @date 2024/5/24 13:48
 * @package top.ytazwc.mooc
 * @description TODO
 */
public class MethodDemo4 {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        list1.add("Hello");

        List<Integer> list2 = new ArrayList<>();
        list2.add(20);

        Class<?> c1 = list1.getClass();
        Class<?> c2 = list2.getClass();

        try {
            Method method = c2.getMethod("add", Object.class);
            Object invoke = method.invoke(list1, 20);
            System.out.println(list1.size());
            System.out.println(list1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(System.out);
        }

    }

}
