package top.ytazwc.thread.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author 花木凋零成兰
 * @title VolatileExample
 * @date 2024/7/8 19:24
 * @package top.ytazwc.thread.volatiles
 * @description 两个线程(主线程和线程A)共享静态变量"stop",主线程期望通过更改变量stop的状态来影响线程A
 * 即让线程A从while(!stop)循环中跳出。去执行第3步;
 */
public class VolatileExample {
    private static volatile boolean stop = false;

    public static void main(String[] args) {
        // Thread-A
        new Thread("Thread A") {
            @Override
            public void run() {
                while (!stop) {
                }
                System.out.println("3: " + Thread.currentThread().getName() + "停止了");
            }
        }.start();
        // Thread-main
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("1: 主线程等待一秒...");
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        System.out.println("2: 将stop变量设置为true");
        stop = true;
    }
}