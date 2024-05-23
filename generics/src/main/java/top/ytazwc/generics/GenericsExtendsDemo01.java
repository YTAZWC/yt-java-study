package top.ytazwc.generics;

/**
 * @author 花木凋零成兰
 * @title GenericsExtendsDemo01
 * @date 2024/5/22 9:13
 * @package top.ytazwc.generics
 * @description 类型边界
 * 类型边界 可以对泛型的类型参数设置限制条件
 * 声明有界类型参数,需要列出类型参数的名称,使用 extends 关键字 后跟限制类或限制接口
 */
public class GenericsExtendsDemo01 {

    public static <T extends Comparable<T>> T max(T x, T y, T z) {
        // 假设x是最大值
        T max = x;
        if (y.compareTo(max) > 0) {
            // 则y更大
            max = y;
        }
        if (z.compareTo(max) > 0) {
            // 则z更大
            max = z;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(max(3, 4, 5));
        System.out.println(max(6.6, 8.8, 7.7));
        System.out.println(max("pear", "apple", "orange"));
    }

}
