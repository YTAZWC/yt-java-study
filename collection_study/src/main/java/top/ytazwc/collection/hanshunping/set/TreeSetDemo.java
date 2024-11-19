package top.ytazwc.collection.hanshunping.set;

import java.util.TreeSet;

/**
 * @author 花木凋零成兰
 * @title TreeSetDemo
 * @date 2024-11-19 22:41
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class TreeSetDemo {
    public static void main(String[] args) {

        /*
        1. 使用无参构造器时，创建 TreeSet 时，仍然是无序的；
        2. 使用 TreeSet 提供的构造器 可以传入一个比较器指定排序规则
         */

        TreeSet<String> treeSet = new TreeSet<>((s1, s2) -> -s1.compareTo(s2));
        treeSet.add("jack");
        treeSet.add("tom");
        treeSet.add("sp");
        treeSet.add("a");
        treeSet.add("a1");

        treeSet.forEach(System.out::println);



    }
}
