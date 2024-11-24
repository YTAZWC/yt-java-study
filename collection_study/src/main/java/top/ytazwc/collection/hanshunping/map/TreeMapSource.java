package top.ytazwc.collection.hanshunping.map;

import java.util.TreeMap;

/**
 * @author 花木凋零成兰
 * @title TreeMapSource
 * @date 2024-11-24 19:19
 * @package top.ytazwc.collection.hanshunping.map
 * @description
 */
public class TreeMapSource {
    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<>();

        map.put("yt", "杨滔");
        map.put("xx", "杨滔");
        map.put("tom", "杨滔");

        map.forEach((k, v) -> System.out.println(k + "=" + v));

    }
}
