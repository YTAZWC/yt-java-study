package top.ytazwc.collection.hanshunping.map;

import java.util.*;

/**
 * @author 花木凋零成兰
 * @title MapFor
 * @date 2024-11-17 21:44
 * @package top.ytazwc.collection.hanshunping.map
 * @description
 */
public class MapFor {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("邓超", "孙俪");
        map.put("王宝强", "马蓉");
        map.put("宋姐", "马蓉");
        map.put("刘玲波", null);
        map.put(null, "刘亦菲");
        map.put("鹿晗", "关晓彤");

        // 遍历方式一：取出所有key，再通过key来遍历
        for (String key : map.keySet()) {
            System.out.println(key + "-" + map.get(key));
        }
        // 迭代
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + "-" + map.get(key));
        }

        // 遍历方式二：取出所有value
        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println(value);
        }

        Iterator<String> iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            String value = iterator1.next();
            System.out.println(value);
        }

        // 遍历方式三：通过 entrySet
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        Iterator<Map.Entry<String, String>> iterator2 = entries.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String, String> entry = iterator2.next();
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }


    }

}
