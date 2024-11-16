package top.ytazwc.collection.hanshunping.set;

import java.util.HashSet;

/**
 * @author 花木凋零成兰
 * @title SetMethod
 * @date 2024-11-14 22:32
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class SetMethod {
    public static void main(String[] args) {

        /*
         * 1. 不可以存放重复的元素，可以添加null
         * 2. 存放数据是无序的，添加的顺序和取出的顺序不一致；但是取出元素的顺序是一致的;
         */

        HashSet<String> set = new HashSet<>();
        set.add("join");
        set.add("lucy");
        set.add(null);
        System.out.println(set);

        set.add("yt");
        System.out.println(set);
        set.forEach(System.out::println);

    }
}
