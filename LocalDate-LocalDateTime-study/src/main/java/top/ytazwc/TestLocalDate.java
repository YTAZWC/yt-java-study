package top.ytazwc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author 花木凋零成兰
 * @title TestLocalDate
 * @date 2024/5/8 9:42
 * @package top.ytazwc
 * @description LocalDate描述的是 年月日
 */
public class TestLocalDate {

    public static void main(String[] args) {

        // 获取当前的年月日
        LocalDate localDate1 = LocalDate.now();
        System.out.println(localDate1);
        // 构造指定的年月日
        LocalDate localDate2 = LocalDate.of(2019, 9, 10);
        System.out.println(localDate2);

        // 获取当前时间的年月日等
        // 获取当前年份
        int year = localDate1.getYear();
        System.out.println(year);
        // 获取当前月份
        Month month = localDate1.getMonth();
        System.out.println(month);
        int monthValue = month.getValue();
        System.out.println(monthValue);
        // 当天 在当年里 是第xxx天
        int dayOfYear = localDate1.getDayOfYear();
        System.out.println(dayOfYear);
        // 获取当天 即在该月内是第 xxx 天
        int dayOfMonth = localDate1.getDayOfMonth();
        System.out.println(dayOfMonth);
        // 获取当天 为星期几
        DayOfWeek dayOfWeek = localDate1.getDayOfWeek();
        System.out.println(dayOfWeek);
        int weekValue = dayOfWeek.getValue();
        System.out.println(weekValue);

    }

}
