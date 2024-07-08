package top.ytazwc.thread.synchronize;

/**
 * @author 花木凋零成兰
 * @title Target
 * @date 2024/7/8 16:12
 * @package top.ytazwc.thread.synchronize
 * @description 加锁和释放锁的原理
 */
public class Target implements Runnable {
    @Override
    public void run() {
        // 同步块
        synchronized (this) {
            System.out.println("测试: 观察Synchronized生成的指令");
        }
    }
    // 同步方法
    public static synchronized void target() {
    }
}
