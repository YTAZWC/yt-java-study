package top.ytazwc.collection.hanshunping.exercise;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 花木凋零成兰
 * @title ExerciseSix
 * @date 2024-11-24 21:16
 * @package top.ytazwc.collection.hanshunping.exercise
 * @description
 */
public class ExerciseSix {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");
        set.add(p1);
        set.add(p2);
        p1.setName("CC");
        set.remove(p1);
        System.out.println(set);
        set.add(new Person(1001, "CC"));
        System.out.println(set);
        set.add(new Person(1001, "AA"));
        System.out.println(set);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    private static final class Person {
        private Integer id;
        private String name;
    }
}
