package top.ytazwc.mooc;

/**
 * @author 花木凋零成兰
 * @title ClassDemo2
 * @date 2024/5/24 10:17
 * @package top.ytazwc.mooc
 * @description TODO
 */
public class ClassDemo2 {

    public static void main(String[] args) {
        // int的类类型
        Class<?> c1 = int.class;
        // String类的类类型
        Class<String> c2 = String.class;
        // double 的类类型
        Class<?> c3 = double.class;
        System.out.println(c3.getName());
        // Double类 的类类型
        Class<Double> c4 = Double.class;
        System.out.println(c4.getName());
        // 输出不含包名的类的类名称
        System.out.println(c4.getSimpleName());
        // false 一个是类的类类型 另一个是基本数据类型的类类型
        System.out.println(c3 == c4);
        // void的类类型
        Class<?> c5 = void.class;
        System.out.println(c5.getName());


    }

}
