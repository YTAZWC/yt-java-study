package top.ytazwc.javacore;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title ReflectFieldDemo
 * @date 2024/5/25 15:01
 * @package top.ytazwc.javacore
 * @description Class 对象提供以下方法获取对象的成员(Field)
 * getFiled - 根据名称获取公有的（public）类成员。可以得到父类的类成员
 * getDeclaredField - 根据名称获取已声明的类成员。但不能得到其父类的类成员。
 * getFields - 获取所有公有的（public）类成员。
 * getDeclaredFields - 获取所有已声明的类成员。
 */
public class ReflectFieldDemo {

    class FieldSpy<T> {
        public boolean[][] b = {
                { false, false },
                { true, true }
        };
        public String name = "ytazwc";
        public List<Integer> list;
        public T val;
        private T[] values;
    }

    public static void main(String[] args) throws NoSuchFieldException {

        Field f1 = FieldSpy.class.getField("b");
        System.out.printf("Type: %s%n", f1.getType());

        Field f2 = FieldSpy.class.getField("name");
        System.out.printf("Type: %s%n", f2.getType());

        Field f3 = FieldSpy.class.getField("list");
        System.out.printf("Type: %s%n", f3.getType());

        Field f4 = FieldSpy.class.getField("val");
        System.out.printf("Type: %s%n", f4.getType());

    }

}
