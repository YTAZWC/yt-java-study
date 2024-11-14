package top.ytazwc.collection.hanshunping.list;

import java.util.ArrayList;

/**
 * @author 花木凋零成兰
 * @title ArrayListDetail
 * @date 2024-11-12 22:28
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class ArrayListDetail {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        // 1、可以放多个 null
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        System.out.println(list);

        // 2、是线程不安全的
    }

}
