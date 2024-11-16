package top.ytazwc.collection.hanshunping.set;

import java.util.HashSet;

/**
 * @author 花木凋零成兰
 * @title HashSetOne
 * @date 2024-11-14 22:57
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class HashSetOne {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        // ok join
        set.add(new String("yt"));
        // no join
        set.add(new String("yt"));
        String s1 = new String("yt");
        String s2 = new String("yt");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s2 == s1);
        System.out.println(s2.equals(s1));
    }
}
