package top.ytazwc.collection.hanshunping.set;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 花木凋零成兰
 * @title HashSetExercise
 * @date 2024-11-16 22:49
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class HashSetExercise {


    public static void main(String[] args) {
        Set<Employee> set = new HashSet<>();
        set.add(new Employee("yt", 21));
        set.add(new Employee("yt", 21));
        set.add(new Employee("yt", 21));

        set.forEach(System.out::println);



    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static final class Employee {
        private String name;
        private Integer age;
    }
}
