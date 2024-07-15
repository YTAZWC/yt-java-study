package top.ytazwc.thread.util;

import java.util.concurrent.CountDownLatch;

/**
 * @author 花木凋零成兰
 * @title CountDownLatchTest
 * @date 2024/7/15 11:32
 * @package top.ytazwc.thread.util
 * @description CountDownLatch允许一个或多个线程等待其他线程完成操作
 *
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 举例:假设主线程需要等待5个子线程分别完成任务之后,再执行总任务,可以利用CountDownLatch实现。
        CountDownLatch cLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; ++ i) {
            String name = "task_" + i;
            new Thread(() -> {
                System.out.println("完成任务: " + Thread.currentThread().getName());
                cLatch.countDown();
            }, name).start();
        }
        cLatch.await();
        System.out.println("5个任务全部完成");
    }
}
