package top.ytazwc.thread;

/**
 * @author 花木凋零成兰
 * @title MyThread
 * @date 2024/7/9 15:36
 * @package top.ytazwc.thread
 * @description 继承Thread类, 并重写了其中的run()方法。
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
