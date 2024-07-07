package top.ytazwc.collection.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 花木凋零成兰
 * @title LinkedListQueueDemo
 * @date 2024/7/7 21:10
 * @package top.ytazwc.collection.queue
 * @description LinkedList 是 Deque 的链表实现。
 */
public class LinkedListQueueDemo {
    public static void main(String[] args) {
        // add() 和 remove() 失败时会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<>();
        // 入队
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        // 遍历
        for (String q : queue) {
            System.out.println(q);
        }

        System.out.println("===");
        // 出队
        System.out.println("poll = " + queue.poll());
        for (String q : queue) {
            System.out.println(q);
        }

        System.out.println("===");
        // 返回第一个元素
        System.out.println("element = " + queue.element());
        for (String q : queue) {
            System.out.println(q);
        }

        System.out.println("===");
        System.out.println("peek = " + queue.peek());
        for (String q : queue) {
            System.out.println(q);
        }
    }
}
