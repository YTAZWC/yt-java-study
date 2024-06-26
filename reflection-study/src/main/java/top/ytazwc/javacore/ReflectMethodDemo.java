package top.ytazwc.javacore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 花木凋零成兰
 * @title ReflectMethodDemo
 * @date 2024/5/25 15:12
 * @package top.ytazwc.javacore
 * @description Class 对象提供以下方法获取对象的方法(Method)
 * getMethod - 返回类或接口的特定方法。其中第一个参数为方法名称，后面的参数为方法参数对应 Class 的对象。
 * getDeclaredMethod - 返回类或接口的特定声明方法。其中第一个参数为方法名称，后面的参数为方法参数对应 Class 的对象。
 * getMethods - 返回类或接口的所有 public 方法，包括其父类的 public 方法。
 * getDeclaredMethods - 返回类或接口声明的所有方法，包括 public、protected、默认（包）访问和 private 方法，但不包括继承的方法
 */
public class ReflectMethodDemo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 返回所有方法
        Method[] methods1 = System.class.getDeclaredMethods();
        System.out.println("System getDeclaredMethods清单(数量 = " + methods1.length + ")");
        for (Method method : methods1) {
            System.out.println(method);
        }

        // 返回所有public方法
        Method[] methods2 = System.class.getMethods();
        System.out.println("System getMethods清单(数量 = " + methods2.length + ")");
        for (Method method : methods2) {
            System.out.println(method);
        }

        // 利用 Method 的 invoke 方法调用 System.currentTimeMillis()
        Method method = System.class.getMethod("currentTimeMillis");
        System.out.println(method);
        System.out.println(method.invoke(null));

    }

}
