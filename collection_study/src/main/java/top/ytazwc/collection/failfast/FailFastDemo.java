package top.ytazwc.collection.failfast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 花木凋零成兰
 * @title FailFastDemo
 * @date 2024/7/6 20:16
 * @package top.ytazwc.collection.failfast
 * @description fail-fast 是 Java 容器的一种错误检测机制。
 * 当多个线程对容器进行结构上的改变的操作时，就可能触发 fail-fast 机制
 * 容器在迭代操作中改变元素个数（添加、删除元素）都可能会导致 fail-fast。
 */
public class FailFastDemo {
    private static int MAX = 100;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < MAX; i++) {
            list.add(i);
        }
        new Thread(new MyThreadA()).start();
        new Thread(new MyThreadB()).start();
    }

    // 迭代遍历容器所有元素
    static class MyThreadA implements Runnable {
        @Override
        public void run() {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                System.out.println("My ThreadA 访问元素:" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
    /* 遍历删除指定范围内的所有偶数 */
    static class MyThreadB implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (i < MAX) {
                if (i % 2 == 0) {
                    System.out.println("My ThreadB 删除元素: " + i);
                    list.remove(i);
                }
                i++;
            }
        }
    }
}
