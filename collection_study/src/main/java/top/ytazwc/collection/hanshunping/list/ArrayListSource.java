package top.ytazwc.collection.hanshunping.list;

import java.util.ArrayList;

/**
 * @author 花木凋零成兰
 * @title ArrayListSource
 * @date 2024-11-12 22:31
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class ArrayListSource {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>(8);
        for (int i = 1; i <= 10; ++ i) {
            list.add(i);
        }

        for (int i = 11; i <= 15; ++ i) {
            list.add(i);
        }
        list.add(200);
        list.add(200);

    }

}
