package top.ytazwc.collection.hanshunping.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 花木凋零成兰
 * @title Map_
 * @date 2024-11-17 20:41
 * @package top.ytazwc.collection.hanshunping.map
 * @description
 */
public class Map_ {

    public static void main(String[] args) {

        /*
        1、用于保存具有映射关系的数据：Key-Value 双列元素
        2、key 不允许重复；value 可以重复
         */

        Map<String, String> map = new HashMap<>();
        map.put("No.1", "yt");
        map.put("No.2", "yl");

        map.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        Set<Map.Entry<String, String>> entries = map.entrySet();

    }

}
