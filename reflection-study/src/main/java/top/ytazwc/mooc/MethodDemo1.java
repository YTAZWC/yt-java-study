package top.ytazwc.mooc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 花木凋零成兰
 * @title MethodDemo1
 * @date 2024/5/24 12:40
 * @package top.ytazwc.mooc
 * @description 通过反射获取某个方法，并执行
 */
public class MethodDemo1 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取print(int,int)方法
        // 1. 获取一个方法就是获取一个类的信息
        // 则需要获取一个类的类类型
        A a1 = new A();
        Class<?> c = a1.getClass();
        // 2. 获取方法 由名称和参数列表来决定
        Method method = c.getMethod("print", int.class, int.class);
        // 方法的反射操作
        // 方法如果有返回值 则返回具体的返回值
        // 如果没有返回值 则返回null
        Object obj = method.invoke(a1, 1, 2);

        System.out.println("==========================");
        Method method1 = c.getMethod("print", String.class, String.class);
        Object invoke = method1.invoke(a1, "ytazwc", "LOVE");

        System.out.println("==========================");
        Method method2 = c.getMethod("print");
        method2.invoke(a1);

    }
}

class A {
    public void print() {
        System.out.println("empty parameter method print()");
    }
    public void print(int a, int b) {
        System.out.println(a + b);
    }
    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());
    }
}
