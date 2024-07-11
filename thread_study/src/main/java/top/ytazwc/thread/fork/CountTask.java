package top.ytazwc.thread.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author 花木凋零成兰
 * @title CountTask
 * @date 2024/7/11 17:28
 * @package top.ytazwc.thread.fork
 * @description 使用Fork/Join框架 计算：1+2+3+4结果
 */
public class CountTask extends RecursiveTask<Integer> {

    // 阈值
    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; ++ i) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值 就分裂成两个任务计算
            int middle = (start + end) >>> 1;
            // 执行子任务
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle, end);
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完毕 并得到返回结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务2 负责计算1+2+3+4
        CountTask task = new CountTask(1, 4);
        // 提交并 执行任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
