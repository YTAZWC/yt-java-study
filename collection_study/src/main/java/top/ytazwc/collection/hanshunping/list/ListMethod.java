package top.ytazwc.collection.hanshunping.list;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 花木凋零成兰
 * @title ListMethod
 * @date 2024-11-12 21:47
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class ListMethod {

    public static void main(String[] args) {

        ArrayList list = new ArrayList();
        // add(E e) 插入元素到末尾
        list.add("周星驰");
        list.add("刘德华");
        System.out.println(list);
        // add(int index, Object e) 从index位置开始将e插入进去
        list.add(1, "yt");
        System.out.println(list);
        // boolean addAll(int index, Collection e)
        list.addAll(1, Arrays.asList("xxx", "aaa"));
        System.out.println(list);



    }

}
