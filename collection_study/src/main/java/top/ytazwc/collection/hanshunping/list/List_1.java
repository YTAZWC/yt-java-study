package top.ytazwc.collection.hanshunping.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title List_1
 * @date 2024-11-12 21:44
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class List_1 {
    public static void main(String[] args) {
        // 1、list 中集合元素有序、且可重复
        List list = new ArrayList();
        list.add("jack");
        list.add("tom");
        list.add("mary");
        System.out.println(list);
        // 2、每个元素都有其对应的顺序索引，从0开始
        System.out.println(list.get(2));
    }
}
