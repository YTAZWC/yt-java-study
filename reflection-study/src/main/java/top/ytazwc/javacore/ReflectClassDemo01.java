package top.ytazwc.javacore;

/**
 * @author 花木凋零成兰
 * @title ReflectClassDemo01
 * @date 2024/5/25 12:18
 * @package top.ytazwc.javacore
 * @description 使用Class.forName静态方法获取Class对象
 */
public class ReflectClassDemo01 {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> c1 = Class.forName("top.ytazwc.javacore.ReflectClassDemo01");
        System.out.println(c1.getCanonicalName());

        Class<?> c2 = Class.forName("[D");
        System.out.println(c2.getCanonicalName());

        Class<?> c3 = Class.forName("[[Ljava.lang.String;");
        System.out.println(c3.getCanonicalName());

    }

}
