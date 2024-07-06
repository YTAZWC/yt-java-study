package top.ytazwc.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 花木凋零成兰
 * @title asListTest
 * @date 2024/7/6 22:09
 * @package top.ytazwc.collection.list
 * @description Arrays.asList 将原始数组转化为List类数据结构 继续展开各种Stream操作
 */
public class asListTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        // 会将int数组整体作为一个对象的泛型类型T
        List<int[]> list = Arrays.asList(arr);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.get(0).getClass());

        // 使用 Arrays.stream 方法来转换
        List<Integer> list1 = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list1.size());
        System.out.println(list1.get(0).getClass());

        // Arrays.asList 转换引用类型数组
        String[] arr2 = {"1", "2", "3"};
        // asList返回的List不支持 增删操作; 因为返回的不是java.util.ArrayList，而是Arrays内部类ArrayList
        List<String> list2 = Arrays.asList(arr2);
        // arr2修改会影响到返回的list2
        // 即对原始数组的修改会影响到 获取到的 List
        arr2[1] = "5";
        System.out.println(list2);

        // 可以重新初始化一个ArraysList来解决该问题
        List<String> list3 = new ArrayList<>(Arrays.asList(arr2));
        arr2[2] = "6";
        System.out.println(list3);

    }
}
