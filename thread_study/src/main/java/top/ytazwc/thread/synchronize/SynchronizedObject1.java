package top.ytazwc.thread.synchronize;

/**
 * @author 花木凋零成兰
 * @title SynchronizedObject1
 * @date 2024/7/8 15:45
 * @package top.ytazwc.thread.synchronize
 * @description 1:对于普通同步方法,锁是当前实例对象
 */
public class SynchronizedObject1 implements Runnable {
    @Override
    public void run() {
        method();
    }

    // synchronized 定义普通方法 默认锁的是实例对象
    public synchronized void method() {
        System.out.println("线程 " + Thread.currentThread().getName() + " 开始");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        System.out.println("线程 " + Thread.currentThread().getName() + " 结束");
    }

    public static void main(String[] args) {
        SynchronizedObject1 instance = new SynchronizedObject1();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }

}
