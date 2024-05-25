package top.ytazwc.javacore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title InstanceofDemo
 * @date 2024/5/25 14:36
 * @package top.ytazwc.javacore
 * @description 判断是否为某个类的实例
 * 1、使用 instanceof 关键字
 * 2、用 Class 对象的 isInstance 方法 (Native)方法
 */
public class InstanceofDemo {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        // 方式1
        if (arrayList instanceof List) {
            System.out.println("ArrayList is List");
        }
        // 方式2
        if (List.class.isInstance(arrayList)) {
            System.out.println("ArrayList is List");
        }
    }

}
