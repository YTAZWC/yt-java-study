package top.ytazwc.collection.hanshunping.exercise;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title ExerciseTwo
 * @date 2024-11-24 21:03
 * @package top.ytazwc.collection.hanshunping.exercise
 * @description
 */
public class ExerciseTwo {

    public static void main(String[] args) {
        Car car1 = new Car("宝马", 400000D);
        Car car2 = new Car("宾利", 5000000D);

        List<Car> list = new ArrayList<>();
        list.add(car1);
        list.add(car2);

        list.forEach(System.out::println);

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
