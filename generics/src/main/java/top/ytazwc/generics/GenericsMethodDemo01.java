package top.ytazwc.generics;

/**
 * @author 花木凋零成兰
 * @title GenericsMethodDemo01
 * @date 2024/5/22 8:40
 * @package top.ytazwc.generics
 * @description 使用泛型方法的时候，通常不必指明类型参数，因为编译器会为我们找出具体的类型
 * 这称为类型参数推断（type argument inference）。类型推断只对赋值操作有效，其他时候并不起作用。
 */
public class GenericsMethodDemo01 {

    public static <T> void printClass(T obj) {
        System.out.println(obj.getClass());
    }

    public static void main(String[] args) {
        printClass(123);
        printClass("abc");
    }

}
