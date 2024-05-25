package top.ytazwc.javacore;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 花木凋零成兰
 * @title NewInstanceDemo
 * @date 2024/5/25 14:42
 * @package top.ytazwc.javacore
 * @description 通过反射创建实例对象
 * 1、使用 Class 对象的 newInstance 方法
 * 2、使用 Constructor 对象的 newInstance 方法
 */
public class NewInstanceDemo {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // 1、使用 Class 对象的 newInstance 方法
        Class<StringBuilder> c1 = StringBuilder.class;
        StringBuilder sb = c1.newInstance();
        sb.append("ytazwc");
        System.out.println(sb);

        // 2、使用 Constructor 对象的 newInstance 方法
        Class<String> c2 = String.class;
        Constructor<String> constructor = c2.getConstructor(String.class);
        // 使用构造器创建实例
        String aaa = constructor.newInstance("aaa");
        System.out.println(aaa);

    }

}
