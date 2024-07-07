package top.ytazwc.collection.map;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author 花木凋零成兰
 * @title TreeMapDemo
 * @date 2024/7/7 20:06
 * @package top.ytazwc.collection.map
 * @description TreeMap 示例
 */
public class TreeMapDemo {
    private static final String[] CHARS = "A B C D F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < CHARS.length; ++ i) {
            treeMap.put(i, CHARS[i]);
        }
        System.out.println(treeMap);
        Integer low = treeMap.firstKey();
        System.out.println(low);
        Integer high = treeMap.lastKey();
        System.out.println(high);

        Iterator<Integer> iterator = treeMap.keySet().iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) {
                low = iterator.next();
            }
            if (i == 6) {
                high = iterator.next();
            } else {
                iterator.next();
            }
        }
        System.out.println(low);
        System.out.println(high);
        System.out.println(treeMap.subMap(low, high));
        // 返回小于 high 的 key
        System.out.println(treeMap.headMap(high));
        // 返回大于 low 的 key
        System.out.println(treeMap.tailMap(low));
    }
}
