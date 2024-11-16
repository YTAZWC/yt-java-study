package top.ytazwc.collection.hanshunping.set;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 花木凋零成兰
 * @title HashSetExerciseTwo
 * @date 2024-11-16 23:01
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class HashSetExerciseTwo {

    public static void main(String[] args) {
        Set<Employee> set = new HashSet<>();
        set.add(new Employee("yt", 22D, new Employee.Birthday(2003, 6, 25)));
        set.add(new Employee("yt", 26D, new Employee.Birthday(2003, 6, 25)));
        set.add(new Employee("yt", 28D, new Employee.Birthday(2003, 6, 25)));
        set.forEach(System.out::println);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode(exclude = {"sal"})
    private static final class Employee {
        private String name;
        private Double sal;
        private Birthday birthday;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        @EqualsAndHashCode
        private static final class Birthday {
            private Integer year;
            private Integer month;
            private Integer day;
        }
    }
}
