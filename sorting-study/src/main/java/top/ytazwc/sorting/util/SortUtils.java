package top.ytazwc.sorting.util;

/**
 * @author 花木凋零成兰
 * @title SortUtils
 * @date 2024/7/3 16:46
 * @package top.ytazwc.sorting.util
 * @description 排序工具
 */
public class SortUtils {

    /**
     * 简单排序
     *
     * @param elements 待排序序列
     * @param n        序列长度
     */
    public static void selectSort(int[] elements, int n) {
        for (int i = 0; i < n-1; ++ i) {
            // 每一趟排序确定一个元素的最终位置
            // 记录最小元素位置
            int min = i;
            for (int j = i+1; j < n; ++ j) {
                if (elements[j] < elements[min]) {
                    min = j;
                }
            }
            if (min != i) {
                // 交换
//                swap(elements[min], elements[i]);
                int t = elements[min];
                elements[min] = elements[i];
                elements[i] = t;
            }
        }
    }

    /**
     * 建立大根堆
     * @param a 序列
     * @param len 序列长度
     */
    public static void buildMaxHeap(int[] a, int len) {
        for (int i = len/2; i > 0; -- i) {
            headAdjust(i, a, len);
        }
    }

    // 对以元素master为根的 子树进行调整
    private static void headAdjust(int master, int[] a, int len) {
        // 暂存根节点
        a[0] = a[master];
        // 调整后，需要重新调整被调整过的树的子树是否合法
        for (int i = master*2; i <= len; i *= 2) {
            if (i < len && a[i] < a[i+1]) {
                ++ i;   // 取较大的子节点
            }
            if (a[0] >= a[i]) {
                // 找到旧根节点 所放位置 结束筛选
                break;
            } else {
                // 找到新的根节点
                a[master] = a[i];
                // 将根节点记录 更换为子节点 继续调整子树
                master = i;
            }
        }
        // 将旧根节点 放在新位置上
        a[master] = a[0];
    }

}
