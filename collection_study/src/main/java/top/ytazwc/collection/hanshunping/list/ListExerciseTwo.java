package top.ytazwc.collection.hanshunping.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title ListExerciseTwo
 * @date 2024-11-12 22:05
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class ListExerciseTwo {

    public static void main(String[] args) {
        List<Book> list = new ArrayList<>();
        list.add(new Book("红楼梦", "曹雪芹", 100));
        list.add(new Book("西游记", "吴承恩", 10));
        list.add(new Book("水浒传", "施耐庵", 9));
        list.add(new Book("三国演义", "罗贯中", 80));
        list.add(new Book("红楼梦", "曹雪芹", 100));
        list.add(new Book("红楼梦", "曹雪芹", 100));

        bubblingSorting(list);

        System.out.println(list);

        for (Book book : list) {
            String name = String.format("%-6s", book.getName());
            String price = String.format("%-6s", book.getPrice());
            String author = String.format("%-6s", book.getAuthor());
            System.out.println("名称: " + name + "价格: " + price + "作者: " + author);
        }

    }

    private static void bubblingSorting(List<Book> list) {
        for (int i = list.size()-1; i >= 0; -- i) {
            for (int j = 0; j < i; ++ j) {
                if (list.get(j).getPrice() > list.get(j+1).getPrice()) {
                    // 交换
                    Book book = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, book);
                }
            }
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
    private Integer price;
}
