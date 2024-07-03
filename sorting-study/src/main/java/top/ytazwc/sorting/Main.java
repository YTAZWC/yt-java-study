package top.ytazwc.sorting;

import top.ytazwc.sorting.util.SortUtils;

import java.util.Arrays;

/**
 * @author 花木凋零成兰
 * @title Main
 * @date 2024/7/3 16:45
 * @package top.ytazwc.sorting
 * @description 主方法
 */
public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{1, 43, 65, 43, 32, 656, 32, 321, 65, 434};
        SortUtils.selectSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
