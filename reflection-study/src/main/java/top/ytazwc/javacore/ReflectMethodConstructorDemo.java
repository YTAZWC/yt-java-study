package top.ytazwc.javacore;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 花木凋零成兰
 * @title ReflectMethodConstructorDemo
 * @date 2024/5/25 15:43
 * @package top.ytazwc.javacore
 * @description Class 对象提供以下方法获取对象的构造方法(Constructor)
 * getConstructor - 返回类的特定 public 构造方法。参数为方法参数对应 Class 的对象。
 * getDeclaredConstructor - 返回类的特定构造方法。参数为方法参数对应 Class 的对象。
 * getConstructors - 返回类的所有 public 构造方法。
 * getDeclaredConstructors - 返回类的所有构造方法。
 * 获取一个 Constructor 对象后，可以用 newInstance 方法来创建类实例
 */
public class ReflectMethodConstructorDemo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<?>[] constructors1 = String.class.getDeclaredConstructors();
        System.out.println("String getDeclaredConstructors 清单(数量 = " + constructors1.length + ")");
        for (Constructor<?> constructor : constructors1) {
            System.out.println(constructor);
        }

        Constructor<?>[] constructors2 = String.class.getConstructors();
        System.out.println("String getConstructors 清单(数量 = " + constructors2.length + ")");
        for (Constructor<?> constructor : constructors2) {
            System.out.println(constructor);
        }

        System.out.println("======================");
        Constructor<String> constructor = String.class.getConstructor(String.class);
        System.out.println(constructor);
        String ytazwc = constructor.newInstance("ytazwc");
        System.out.println(ytazwc);

    }

}
