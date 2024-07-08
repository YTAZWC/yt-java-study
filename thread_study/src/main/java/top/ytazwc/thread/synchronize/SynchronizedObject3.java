package top.ytazwc.thread.synchronize;

/**
 * @author 花木凋零成兰
 * @title SynchronizedObject3
 * @date 2024/7/8 16:01
 * @package top.ytazwc.thread.synchronize
 * @description 对于静态同步方法, 锁是当前类的Class对象
 */
public class SynchronizedObject3 implements Runnable {

    Object lockTarget = new Object();

    @Override
    public void run() {
        method();
    }

    // synchronized 用在静态方法上 默认的锁是当前所在的 Class 类
    public static synchronized void method() {
        System.out.println("线程 " + Thread.currentThread().getName() + " 开始");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        System.out.println("线程 " + Thread.currentThread().getName() + " 结束");
    }

    public static void main(String[] args) {
        SynchronizedObject3 instance1 = new SynchronizedObject3();
        SynchronizedObject3 instance2 = new SynchronizedObject3();
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }
}
