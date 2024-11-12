package top.ytazwc.collection.hanshunping.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title CollectionPractice
 * @date 2024-10-24 22:21
 * @package top.ytazwc.collection.hanshunping.collection
 * @description 练习题
 */
public class CollectionPractice {

    public static void main(String[] args) {

        List<Dog> list = new ArrayList<>();
        list.add(new Dog("小黑", 3));
        list.add(new Dog("小白", 5));
        list.add(new Dog("小彩", 2));

        Iterator<Dog> iterator = list.iterator();
        while (iterator.hasNext()) {
            Dog next = iterator.next();
            System.out.println(next);
        }

        for (Dog dog : list) {
            System.out.println(dog);
        }

    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Dog {
    private String name;
    private int age;
}
