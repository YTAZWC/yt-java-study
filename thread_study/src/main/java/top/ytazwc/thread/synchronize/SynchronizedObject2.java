package top.ytazwc.thread.synchronize;

/**
 * @author 花木凋零成兰
 * @title SynchronizedObject2
 * @date 2024/7/8 15:51
 * @package top.ytazwc.thread.synchronize
 * @description 对于同步方法块,锁是Synchronized括号里面指定的对象
 */
public class SynchronizedObject2 implements Runnable{

    final Object lockTarget = new Object();

    @Override
    public void run() {
        // 同步方法块 锁指定 lockTarget
        synchronized (lockTarget) {
            System.out.println("线程 " + Thread.currentThread().getName() + " 开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.out.println("线程 " + Thread.currentThread().getName() + " 结束");
        }
    }

    public static void main(String[] args) {
        SynchronizedObject2 instance = new SynchronizedObject2();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }
}
