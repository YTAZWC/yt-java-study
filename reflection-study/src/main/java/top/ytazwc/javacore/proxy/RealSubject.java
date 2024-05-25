package top.ytazwc.javacore.proxy;

/**
 * @author 花木凋零成兰
 * @title RealSubject
 * @date 2024/5/25 19:17
 * @package top.ytazwc.javacore.proxy
 * @description 真实调用的类，即被代理的类
 */
public class RealSubject extends Subject {
    @Override
    public void request() {
        System.out.println("真实的请求...");
    }
}
