package top.ytazwc.generics;

/**
 * @author 花木凋零成兰
 * @title GenericsExtendsDemo02
 * @date 2024/5/22 9:19
 * @package top.ytazwc.generics
 * @description 可以设置多个类型边界
 * 语法形式: <T extends A & B & C>
 * extends关键字后面的第一个参数类型可以是类或接口, 但是其他类型参数只能是接口
 */
public class GenericsExtendsDemo02 {

    class A {
    }
    class A1 {}

    interface B {
    }

    interface C {
    }

    static class D1<T extends A & B & C> {

    }

    static class D2<T extends B & C> {

    }

    class E extends A implements B, C {

    }

    public static <T extends A & B & C> void f1(T x) {
    }

    public static void main(String[] args) {

        D1<E> d1 = new D1<>();
        D2<E> d2 = new D2<>();

        System.out.println(d1.getClass());

    }

}
