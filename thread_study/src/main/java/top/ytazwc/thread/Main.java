package top.ytazwc.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 花木凋零成兰
 * @title Main
 * @date 2024/7/8 9:52
 * @package top.ytazwc.thread
 * @description 多个线程对同一个共享数据进行访问而不采取同步操作的话活, 那么操作的结果是不一致的。
 * 以下代码演示了500个线程同时对cnt执行自增操作,操作结束之后它的值有可能小于500。
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 500;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        // CountDownLatch 并发工具类 保证线程池线程完成500次累加
        final CountDownLatch countDownLatch  = new CountDownLatch(threadSize);
        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; ++ i) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }
}
class ThreadUnsafeExample {
    private int count = 0;

    public void add() {
        count ++;
    }

    public int get() {
        return count;
    }
}
