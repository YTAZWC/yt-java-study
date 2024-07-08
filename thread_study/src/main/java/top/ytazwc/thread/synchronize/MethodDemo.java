package top.ytazwc.thread.synchronize;

/**
 * @author 花木凋零成兰
 * @title MethodDemo
 * @date 2024/7/8 16:31
 * @package top.ytazwc.thread.synchronize
 * @description 可重入的实现原理
 * synchronized对于一个对象加锁后是可以重入的,就是说同一个线程可以反复给该对象加锁
 * 且并不会因为前一次加的锁还没有释放而阻塞。
 */
public class MethodDemo {

    public static void main(String[] args) {
        MethodDemo demo = new MethodDemo();
        demo.method1();
    }

    private synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + ": method1");
        method2();
    }

    private synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + ": method2");
        method3();
    }

    private synchronized void method3() {
        System.out.println(Thread.currentThread().getName() + ": method3");
    }

}
