package top.ytazwc.collection.hanshunping.map;

import java.util.HashMap;

/**
 * @author 花木凋零成兰
 * @title HashMaoSourceOne
 * @date 2024-11-18 20:50
 * @package top.ytazwc.collection.hanshunping.map
 * @description
 */
public class HashMaoSourceOne {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("java", 10);
        map.put("php", 10);
        map.put("java", 20);

        map.forEach((k, v) -> System.out.println(k + " = " + v));

    }

}
