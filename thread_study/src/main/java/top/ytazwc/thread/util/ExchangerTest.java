package top.ytazwc.thread.util;

import java.util.concurrent.Exchanger;

/**
 * @author 花木凋零成兰
 * @title ExchangerTest
 * @date 2024/7/15 20:41
 * @package top.ytazwc.thread.util
 * @description 线程间交换数据的Exchanger
 * Exchanger(交换者)是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换。
 * 它提供一个同步点,在这个同步点,两个线程可以交换彼此的数据。这两个线程通过
 * exchange方法交换数据,如果第一个线程先执行exchange()方法,它会一直等待第二个线程
 * 也执行exchange方法,当两个线程都到达同步点时,这两个线程就就可以交换数据,将本线程
 * 生产出来的数据传递给对方。
 */
public class ExchangerTest {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                String a = "你好,我是AAA";
                String b = exchanger.exchange(a);
                System.out.println("我是线程: " + Thread.currentThread().getName() + ", 我收到的消息是: " + b);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "A").start();
        new Thread(() -> {
            try {
                String a = "你好,我是BBB";
                String b = exchanger.exchange(a);
                System.out.println("我是线程: " + Thread.currentThread().getName() + "我收到的消息是: " + b);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "B").start();
    }
}
