package top.ytazwc.collection.hanshunping.set;

import java.util.HashSet;

/**
 * @author 花木凋零成兰
 * @title HashSetSrouce
 * @date 2024-11-16 17:46
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class HashSetSource {

    public static void main(String[] args) {

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("String");
        hashSet.add("Integer");
        hashSet.add("Double");

        hashSet.forEach(System.out::println);

    }

}
