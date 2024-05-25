package top.ytazwc.javacore.proxy;

/**
 * @author 花木凋零成兰
 * @title Proxy
 * @date 2024/5/25 19:18
 * @package top.ytazwc.javacore.proxy
 * @description 保存了一个引用，使代理可以访问实体
 * 并提供一个与Subject一样的接口，如此代理就可以用来替代实体
 */
public class Proxy extends Subject {
    private RealSubject real;
    @Override
    public void request() {
        if (real == null) {
            real = new RealSubject();
        }
        System.out.println("调用真实方法前,进行处理...");
        real.request();
        System.out.println("调用真实方法后,进行处理... ");
    }
}
