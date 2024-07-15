package top.ytazwc.thread.util;

import java.util.concurrent.Semaphore;

/**
 * @author 花木凋零成兰
 * @title SemaphoreTest
 * @date 2024/7/15 12:00
 * @package top.ytazwc.thread.util
 * @description Semaphore(信号量)是用来控制同时访问特定资源的线程数量, 它通过协调各个线程, 以
 * 保证合理地使用公共资源。
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= 20; i++) {
            String name = "task_" + i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("执行任务: " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, name).start();
        }
        Thread.sleep(20000);
    }
}
