package top.ytazwc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 花木凋零成兰
 * @title MyCallable
 * @date 2024/7/9 15:31
 * @package top.ytazwc.thread
 * @description 与Runnable相比, Callable可以有返回值, 返回值通过FutureTask进行封装。
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 123;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable callable = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(callable);
        Thread th = new Thread(ft);
        th.start();
        System.out.println(ft.get());
    }

}
