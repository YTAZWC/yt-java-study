package top.ytazwc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 花木凋零成兰
 * @title InterruptedTest
 * @date 2024/7/9 15:45
 * @package top.ytazwc.thread
 * @description 如果sleep、wait等可以让线程进入阻塞的方法使线程休眠了,而上于休眠中的线程被中断,
 * 那么线程是可以感受到中断信号的,并且会抛出一个InterruptedException异常
 * 在抛出InterruptException之前,Java虚拟机会将该线程的中断标记位清除
 * 然后抛出InterruptException,此时调用isInterrupted()方法将会返回false。
 */
public class InterruptedTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new TestThread1();
        thread1.start();
        TimeUnit.SECONDS.sleep(2);
        thread1.interrupt();
    }

    private static class TestThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(20000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
                System.out.println("catch: " + Thread.currentThread().isInterrupted());
            }
        }
    }
}
