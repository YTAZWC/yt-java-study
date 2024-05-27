package top.ytazwc.javacore;

/**
 * @author 花木凋零成兰
 * @title FunctionalInterfaceAnnotationDemo
 * @date 2024/5/27 10:19
 * @package top.ytazwc.javacore
 * @description @FunctionalInterface 用于表示被修饰的接口是函数式接口
 * 若修饰的不是函数式接口，则编译器会报错
 *
 * 函数式结果特点：
 * 1、接口有且只有一个抽象方法，可以有多个非抽象方法
 * 2、不能在接口中覆写Object类中的public方法
 * 3、允许有default实现方法
 */
public class FunctionalInterfaceAnnotationDemo {
    @FunctionalInterface
    public interface Func1<T> {
        void printMessage(T message);
    }
//    @FunctionalInterface    // 接口中定义两个抽象方法 编译时会报错
//    public interface Func2<T> {
//        void printMessage(T message);
//        void printMessage2(T message);
//    }

    public static void main(String[] args) {
        Func1 func1 = System.out::println;
        func1.printMessage("Hello");
        func1.printMessage(100);
    }

}
