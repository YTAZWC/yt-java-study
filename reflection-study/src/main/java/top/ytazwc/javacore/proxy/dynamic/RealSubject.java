package top.ytazwc.javacore.proxy.dynamic;

/**
 * @author 花木凋零成兰
 * @title RealSubject
 * @date 2024/5/25 20:58
 * @package top.ytazwc.javacore.proxy.dynamic
 * @description 真实对象，实现Subject接口
 */
public class RealSubject implements Subject {
    @Override
    public void hello(String str) {
        System.out.println("real Hello " + str);
    }

    @Override
    public String bye() {
        System.out.println("real Goodbye");
        return "real over";
    }
}
