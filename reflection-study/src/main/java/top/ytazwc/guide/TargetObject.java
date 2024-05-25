package top.ytazwc.guide;

/**
 * @author 花木凋零成兰
 * @title TargetObject
 * @date 2024/5/24 8:41
 * @package top.ytazwc.guide
 * @description 反射目标类
 */
public class TargetObject {

    private String value;

    public TargetObject() {
        value = "ytazwc";
    }

    public void publicMethod(String s) {
        System.out.println("I love " +s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }


}
