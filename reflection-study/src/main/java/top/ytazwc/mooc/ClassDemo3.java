package top.ytazwc.mooc;

/**
 * @author 花木凋零成兰
 * @title ClassDemo3
 * @date 2024/5/24 10:40
 * @package top.ytazwc.mooc
 * @description TODO
 */
public class ClassDemo3 {

    public static void main(String[] args) {

        String s = "Hello Reflect";
        ClassUtils.printClassMethodMessage(s);

        System.out.println("*************************************");

        Integer n1 = 1;
        ClassUtils.printClassMethodMessage(n1);

    }

}
