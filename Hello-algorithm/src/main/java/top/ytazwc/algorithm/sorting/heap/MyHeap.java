package top.ytazwc.algorithm.sorting.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title MyHeap
 * @date 2024-10-01 15:03
 * @package top.ytazwc.algorithm.sorting.heap
 * @description 自定义堆-使用数组来存储堆
 * 则对于索引 i，其左子节点索引为2i+1，其右子节点索引为2i+2，其父节点索引为(i-1)/2
 */
public class MyHeap {

    List<Integer> maxHeap;

    MyHeap(List<Integer> num) {
        // 列表原封不动添加进堆中
        maxHeap = new ArrayList<>(num);
        // 堆化除叶子节点以外的所有节点
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /* 元素出堆 */
    int pop() {
        // 判空处理
        if (maxHeap.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        // 交换根节点与最右叶节点 (交换首元素与尾元素)
        swap(0, size()-1);
        // 删除节点
        int val = maxHeap.remove(size()-1);
        // 从顶至地堆化
        siftDown(0);
        return val;
    }
    void siftDown(int i) {
        while (true) {
            // 获取左右子节点
            int left = left(i);
            int right = right(i);
            int ma = i;
            if (left < size() && maxHeap.get(left) > maxHeap.get(ma)) {
                ma = left;
            }
            if (right < size() && maxHeap.get(right) > maxHeap.get(ma)) {
                ma = right;
            }
            // 若越过叶子节点或完成堆化 则跳出循环
            if (ma == i) {
                break;
            }
            // 交换两节点
            swap(i, ma);
            // 循环向下堆化
            i = ma;
        }
    }

    /* 插入元素到堆中 */
    void push(int val) {
        // 添加节点
        maxHeap.add(val);
        // 从底至顶堆化
        siftUp(size() - 1);
    }
    /* 从节点 i 开始，从底部至顶堆化 */
    void siftUp(int i) {
        while (true) {
            // 获取节点 i 的父节点
            int p = parent(i);
            // 当“越过根节点”或节点无须修复时 结束堆化
            if (p < 0 || maxHeap.get(i) <= maxHeap.get(p)) {
                break;
            }
            // 插入节点 大于 父节点，即需要堆化 交换两节点
            swap(i, p);
            // 继续向上堆化
            i = p;
        }
    }

    /* 交换两节点 */
    void swap(int i, int j) {
        int a = maxHeap.get(i);
        int b = maxHeap.get(j);
        maxHeap.set(i, b);
        maxHeap.set(j, a);
    }

    /* 获取堆元素个数 */
    int size() {
        return maxHeap.size();
    }

    /* 获取堆顶元素 */
    int peek() {
        return maxHeap.get(0);
    }

    /* 索引映射函数-获取左子节点 */
    int left(int i) {
        return 2*i + 1;
    }

    /* 索引映射函数-获取右子节点 */
    int right(int i) {
        return 2*i + 2;
    }

    /* 索引映射函数-获取父节点 */
    int parent(int i) {
        // 向下取整
        return (i-1)/2;
    }
}
