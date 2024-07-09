package top.ytazwc.thread;

/**
 * @author 花木凋零成兰
 * @title RunnableThread
 * @date 2024/7/9 15:25
 * @package top.ytazwc.thread
 * @description 首先通过RunnableThread类实现Runnable接口, 然后重写run()方法
 * 之后只需要把这个实现了run()方法的实例传到Thread类中就可以实现多线程。
 */
public class RunnableThread implements Runnable {
    @Override
    public void run() {
        System.out.println("用实现Runnable接口实现线程");
    }

    public static void main(String[] args) {
        RunnableThread instance = new RunnableThread();
        Thread thread = new Thread(instance);
        thread.start();
    }
}
