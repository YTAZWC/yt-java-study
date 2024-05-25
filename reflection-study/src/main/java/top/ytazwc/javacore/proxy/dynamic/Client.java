package top.ytazwc.javacore.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author 花木凋零成兰
 * @title Client
 * @date 2024/5/25 21:04
 * @package top.ytazwc.javacore.proxy.dynamic
 * @description TODO
 */
public class Client {
    public static void main(String[] args) {
        // 需要代理的真实对象实例
        Subject realSubject = new RealSubject();
        // 需要代理哪个真实对象，则将该对象传进去，然后通过该真实对象来调用其方法
        InvocationHandler handler = new InvocationHandlerDemo(realSubject);
        /*
         * 通过Proxy的newProxyInstance方法来创建代理对象
         * 1、第一个参数，使用handler这个类的ClassLoader对象来加载代理对象
         * 2、第二个参数，为代理对象提供的接口是真实对象所实行的接口，表面要代理的是该真实对象，如此能调用该组接口中的方法
         * 3、第三个参数，将代理对象关联到上方的invocationHandler对象上
         */
        Subject subject = (Subject)Proxy.newProxyInstance(
                handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(),
                handler
        );
        System.out.println(subject.getClass().getName());
        subject.hello("World");
        String bye = subject.bye();
        System.out.println("Result is: " + bye);
    }
}
