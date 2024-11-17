package top.ytazwc.collection.hanshunping.set;

import java.util.LinkedHashSet;

/**
 * @author 花木凋零成兰
 * @title LinkedHashSetSource
 * @date 2024-11-17 11:20
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class LinkedHashSetSource {
    public static void main(String[] args) {

        /*
        1. 加入元素顺序和取出元素顺序一致
        2. 底层维护了一个LinkedHashMap
        3. LinkedHashMap 底层结构(数组+双向链表)
         */
        LinkedHashSet<String> set = new LinkedHashSet<>();

        set.add("String");

        set.forEach(System.out::println);

    }
}
