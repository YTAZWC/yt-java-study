package top.ytazwc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 花木凋零成兰
 * @title ThreadLocalTest
 * @date 2024/7/9 16:19
 * @package top.ytazwc.thread
 * @description 一个简单的例子:通过threadLocal实现打印一段代码的运行时间
 */
public class ThreadLocalTest {
    private static class PrintTime {
        // 创建一个ThreadLocal对象
        private static final ThreadLocal<Long> TIME_THREADLOCAL_OBJECT = new ThreadLocal<>();

        public static void begin() {
            // set 方法 只需要value值 无需传key 因为TIME_THREADLOCAL_OBJECT会作为key
            TIME_THREADLOCAL_OBJECT.set(System.currentTimeMillis());
        }

        public static long end() {
            // get方法无需穿key 因为TIME_THREADLOCAL_OBJECT对象会作为key
            return System.currentTimeMillis()-TIME_THREADLOCAL_OBJECT.get();
        }

        public static void main(String[] args) throws InterruptedException {
            PrintTime.begin();
            TimeUnit.SECONDS.sleep(2);
            long time = PrintTime.end();
            System.out.println(time + " 毫秒");
        }

    }
}
