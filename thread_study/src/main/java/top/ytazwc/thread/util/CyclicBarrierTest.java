package top.ytazwc.thread.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 花木凋零成兰
 * @title CyclicBarrierTest
 * @date 2024/7/15 11:38
 * @package top.ytazwc.thread.util
 * @description 同步屏障CyclicBarrier
 * CyclicBarrier的字面意思可循环使用(Cyclic)的屏障(Barrier)。它要做的事情是,让一组
 * 线程到达一个屏障(也可以叫同步点)时被阻塞,直到最后一个个线程到达屏障时,屏障才会开
 * 门,所有被屏障拦截的线程才会继续运行。
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        // 代码示例:11个空位,主线程和子线程看成一样的,不用区别理里解。前10个线程到达之
        // 后只能等待,等到第11个线程到来之后,全部一起执行。
        CyclicBarrier cb = new CyclicBarrier(11);
        for (int i = 1; i <= 10; ++ i) {
            String name = "task_" + i;
            new Thread(() -> {
                System.out.println("任务: " + Thread.currentThread().getName() + " 正在等待...");
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("完成任务: " + Thread.currentThread().getName());
            }, name).start();
        }
        Thread.sleep(200);
        System.out.println("任务11还有5秒钟才到来,其他线程先等待 ... ");
        Thread.sleep(5000);
        cb.await();
        System.out.println("主线程作为第11个线程一起执行 ... ");
    }
}
