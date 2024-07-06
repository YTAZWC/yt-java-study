package top.ytazwc.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title subListTest
 * @date 2024/7/6 22:24
 * @package top.ytazwc.collection.list
 * @description List.subList
 * List.subList 直接引用了原始的 List，也可以认为是共享“存储”
 * 而且对原始 List 直接进行结构性修改会导致 SubList 出现异常。
 */
public class subListTest {
    public static List<List<Integer>> data = new ArrayList<>();
    public static void main(String[] args) {
        // 会出现 OOM 异常 因为具有100万个元素的List始终无法回收 被subList方法返回的list强引用
//        for (int i = 0; i < 1000; i++) {
//            List<Integer> rawList = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());
//            data.add(rawList.subList(0, 1));
//        }

        // 同样可以使用new ArrayList来解决 创建新的实例


    }
}
