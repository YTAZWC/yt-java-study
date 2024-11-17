package top.ytazwc.collection.hanshunping.map;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 花木凋零成兰
 * @title MapExercise
 * @date 2024-11-17 21:56
 * @package top.ytazwc.collection.hanshunping.map
 * @description
 */
public class MapExercise {


    public static void main(String[] args) {
        Map<Integer, Employee> map = new HashMap<>();
        Employee e1 = new Employee("aaa", 2050D, 1);
        Employee e2 = new Employee("bbb", 207770D, 2);
        Employee e3 = new Employee("ccc", 203423420D, 3);
        map.put(e1.getId(), e1);
        map.put(e2.getId(), e2);
        map.put(e3.getId(), e3);

        map.forEach((key, value) -> {
            if (value.getSalary() > 18000D) {
                System.out.println(value);
            }
        });

    }


    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static final class Employee {
        private String name;
        private Double salary;
        private Integer id;
    }
}
