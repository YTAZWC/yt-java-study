package top.ytazwc.collection.hanshunping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title CollectionsTest
 * @date 2024-11-24 19:37
 * @package top.ytazwc.collection.hanshunping
 * @description
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("jack");
        list.add("jack");
        list.add("jack");
        list.add("smith");
        list.add("king");
        list.add("milan");

        // 反转
        Collections.reverse(list);
        System.out.println(list);

        // 对集合元素进行随机排序
        Collections.shuffle(list);
        System.out.println(list);

        // 按照元素自然顺序进行排序
        Collections.sort(list);
        System.out.println(list);

        // 按照元素长度进行排序
//        Collections.sort(list, (Comparator.comparingInt(String::length)));
        list.sort(Comparator.comparingInt(String::length));
        System.out.println(list);

        // 返回列表中 元素自然顺序的最大值
        String max = Collections.max(list);
        System.out.println(max);

        // 返回列表中长度最大的元素
        String maxL = Collections.max(list, Comparator.comparingInt(String::length));
        System.out.println(maxL);

        // 返回指定集合中指定元素的出现次数
        int frequency = Collections.frequency(list, "jack");
        System.out.println(frequency);

        // 将列表元素复制到另外一个列表中
        // 拷贝时 dest 大小不小于 list
        List<String> copyList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            copyList.add("");
        }
        Collections.copy(copyList, list);
        System.out.println(copyList);

        // 替换列表中的所有指定的值
        Collections.replaceAll(list, "jack", "杰克");
        System.out.println(list);

    }
}
