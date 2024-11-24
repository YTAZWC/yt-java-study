package top.ytazwc.collection.hanshunping.exercise;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 花木凋零成兰
 * @title ExerciseOne
 * @date 2024-11-24 20:47
 * @package top.ytazwc.collection.hanshunping.exercise
 * @description
 */
public class ExerciseOne {
    public static void main(String[] args) {

        New new1 = new New("新冠确诊病例超千万，数百万印度信徒赴恒河 ”圣浴” 引民众担忧");
        New new2 = new New("男子突然想起2个月前的鱼还在网兜里，捞起一看赶紧放生");

        List<New> list = new ArrayList<>();
        list.add(new1);
        list.add(new2);
        for (int i = list.size()-1; i >= 0; -- i) {
            String title = list.get(i).getTitle();
            if (title.length() <= 15) {
                System.out.println(title);
            } else {
                System.out.println(title.substring(0, 15) + "...");
            }
        }

    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode
    private static final class New {
        private String title;
        private String content;

        public New(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
