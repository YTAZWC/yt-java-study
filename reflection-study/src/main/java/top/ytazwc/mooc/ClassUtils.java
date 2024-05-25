package top.ytazwc.mooc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 花木凋零成兰
 * @title ClassUtils
 * @date 2024/5/24 10:28
 * @package top.ytazwc.mooc
 * @description TODO
 */
public class ClassUtils {

    /**
     * 打印类的信息,成员函数
     * @param obj 某个类实例
     */
    public static void printClassMethodMessage(Object obj) {
        // 首先获取类的类类型
        // 传递的是哪个子类的类类型 c就是该子类的类类型
        Class<?> c = obj.getClass();
        // 获取类的名称
        System.out.println("类的名称是: " + c.getName());
        /*
         * 获取类的方法
         * Method类,方法对象
         * 一个成员方法就是一个Method对象
         * getMethods()获取的是所有public的函数，包括从父类继承来的
         * getDeclaredMethods()获取的是该类自己声明的所有方法 不包括父类的
         */
        Method[] methods = c.getMethods();
        // 遍历所有方法
        for (Method method : methods) {
            // 打印方法的返回值类型的 类类型
            Class<?> returnType = method.getReturnType();
            System.out.print(returnType.getName() + " ");
            // 方法名
            System.out.print(method.getName() + "(");
            // 获取参数类型 -> 得到的是参数列表的类型的类类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.printf(parameterType.getName() + ", ");
            }
            System.out.println(")");
        }

    }

    /**
     * 获取类的成员变量的信息
     * @param obj
     */
    public static void printClassFieldMessage(Object obj) {
        Class<?> c = obj.getClass();
        /*
         * 成员变量也是对象
         * java.lang.reflect.Field
         * Field类封装了关于成员变量的操作
         * getFields()方法获取的是所有的public的成员变量的信息
         * getDeclaredFields()获取的是该类自己声明的成员变量的信息
         */
        // Field[] fields = c.getFields();
        Field[] declaredFields = c.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 得到成员变量的类型的类类型
            Class<?> type = declaredField.getType();
            // 获取类型名
            String typeName = type.getName();
            // 获取成员变量的名称
            String fieldName = declaredField.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    /**
     * 打印对象的构造函数的信息
     * @param obj
     */
    public static void printConMessage(Object obj) {
        Class<?> c = obj.getClass();
        /*
         * 构造函数也是对象
         * java.lang.Constructor中封装了构造函数的信息
         * getConstructors获取所有的public的构造函数
         * getDeclaredConstructors获取所有的构造函数 包含私有
         */
        // Constructor<?>[] constructors = c.getConstructors();
        Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.print(declaredConstructor.getName() + "(");
            // 获取构造函数的参数列表 ->  得到的是参数列表的类类型
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ", ");
            }
            System.out.println(")");
        }

    }

}
