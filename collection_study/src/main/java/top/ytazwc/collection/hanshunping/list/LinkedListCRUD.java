package top.ytazwc.collection.hanshunping.list;

import java.util.LinkedList;

/**
 * @author 花木凋零成兰
 * @title LinkedListCRUD
 * @date 2024-11-14 22:02
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class LinkedListCRUD {


    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(1);
        list.add(1);

        list.remove();

        list.set(0, 2);

        list.forEach(System.out::println);


    }

}
