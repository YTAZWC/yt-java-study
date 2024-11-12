package top.ytazwc.collection.hanshunping.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author 花木凋零成兰
 * @title CollectionIterator
 * @date 2024-10-24 22:09
 * @package top.ytazwc.collection.hanshunping.collection
 * @description 集合迭代器示例
 */
public class CollectionIterator {

    public static void main(String[] args) {

        Collection<Book> col = new ArrayList<>();

        col.add(new Book("三国演义", "罗贯中", 10.1));
        col.add(new Book("小李飞刀", "古龙", 5.1));
        col.add(new Book("红楼梦", "曹雪芹", 10.1));

        // 1、获得迭代器
        Iterator<Book> iterator = col.iterator();
        // 2. 使用 while 循环遍历
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 如果需要再次遍历 需要重置迭代器
        iterator = col.iterator();

        // 增强 for 循环；底层依旧是迭代器
        for (Book book : col) {
            System.out.println(book);
        }

    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Book {
    private String name;
    private String author;
    private double price;
}
