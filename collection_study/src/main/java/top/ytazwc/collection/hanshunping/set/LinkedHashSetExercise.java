package top.ytazwc.collection.hanshunping.set;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 花木凋零成兰
 * @title LinkedHashSetExercise
 * @date 2024-11-17 20:26
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class LinkedHashSetExercise {
    public static void main(String[] args) {

        Set<Car> set = new LinkedHashSet<>();

        set.add(new Car("奥拓", 1000d));
        set.add(new Car("奥迪", 300000d));
        set.add(new Car("法拉利", 10000000d));
        set.add(new Car("奥迪", 300000d));
        set.add(new Car("保时捷", 70000000d));
        set.add(new Car("奥迪", 300000d));

        set.forEach(System.out::println);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    private static final class Car {
        private String name;
        private Double price;
    }
}
