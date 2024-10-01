package top.ytazwc.algorithm.sorting.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author 花木凋零成兰
 * @title HeapTest
 * @date 2024-10-01 14:46
 * @package top.ytazwc.algorithm.sorting.heap
 * @description 测试数据结构-优先队列-堆
 */
public class HeapTest {
    public static void main(String[] args) {
        /* 初始化堆 */
        /* 初始化小顶堆 */
        Queue<Integer> minHeap = new PriorityQueue<>();
        /* 初始化大顶堆 */
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        /* 元素入堆 */
        maxHeap.offer(1);
        maxHeap.offer(5);
        maxHeap.offer(3);
        maxHeap.offer(2);
        maxHeap.offer(4);

        /* 获取堆顶元素 不弹出 */
        Integer peek = maxHeap.peek();
        System.out.println("堆顶元素为：" + peek);

        /* 获取堆元素个数 */
        int size = maxHeap.size();
        System.out.println("堆中元素个数：" + size);

        /* 判断堆是否为空 */
        boolean empty = maxHeap.isEmpty();
        System.out.println(empty);

        /* 堆顶元素 依次出栈 */
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}
