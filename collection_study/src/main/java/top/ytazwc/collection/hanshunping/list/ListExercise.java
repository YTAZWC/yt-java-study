package top.ytazwc.collection.hanshunping.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 花木凋零成兰
 * @title ListExercise
 * @date 2024-11-12 21:56
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class ListExercise {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");

        list.add(2, "韩顺平教育");
        System.out.println(list);
        System.out.println(list.get(4));
        System.out.println(list.remove(5));
        System.out.println(list.set(6, "set"));

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }

    }

}
