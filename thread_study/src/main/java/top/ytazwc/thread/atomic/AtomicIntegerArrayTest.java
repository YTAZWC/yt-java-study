package top.ytazwc.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author 花木凋零成兰
 * @title AtomicIntegerArrayTest
 * @date 2024/7/15 11:14
 * @package top.ytazwc.thread.atomic
 * @description TODO
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{0, 1, 2, 3});
        System.out.println(atomicIntegerArray);
        System.out.println(atomicIntegerArray.get(1));
        System.out.println(atomicIntegerArray.compareAndSet(1, 1, 5));
        System.out.println(atomicIntegerArray);
    }
}
