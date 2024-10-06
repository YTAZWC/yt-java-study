package top.ytazwc.thread.deadlock;

/**
 * @author 花木凋零成兰
 * @title DeadLockSample
 * @date 2024-10-06 19:49
 * @package top.ytazwc.thread.deadlock
 * @description 死锁示例
 */
public class DeadLockSample extends Thread {
    private String first;
    private String second;
    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("t1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("t2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
