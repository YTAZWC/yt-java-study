package top.ytazwc.javacore.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 花木凋零成兰
 * @title InvocationHandlerDemo
 * @date 2024/5/25 20:59
 * @package top.ytazwc.javacore.proxy.dynamic
 * @description 自定义动态代理类，必须实现InvocationHandler接口
 */
public class InvocationHandlerDemo implements InvocationHandler {
    // 需要代理的真实对象
    private Object subject;
    // 构造方法，用于给需要代理的真实对象赋初值
    public InvocationHandlerDemo(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理真实对象前 添加一些自己的操作
        System.out.println("Before method...");
        System.out.println("Call Method: " + method);
        // 当代理对象调用真实对象的方法时 会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object obj = method.invoke(subject, args);
        // 在代理真实对象后，可以添加一些自己的操作
        System.out.println("After method...");
        System.out.println();
        return obj;
    }
}
