package top.ytazwc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author 花木凋零成兰
 * @title TestLocalDateTime
 * @date 2024/5/8 10:11
 * @package top.ytazwc
 * @description 测试LocalDateTime
 */
public class TestLocalDateTime {

    public static void main(String[] args) {
        // 获取当前日期 年月日时分秒
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        // 设置指定的日期时间 年月日时分秒
        LocalDateTime localDateTime1 = LocalDateTime.of(2024, 5, 7, 15, 22, 34);
        System.out.println(localDateTime1);
        // 根据LocalDate 和 LocalTime 得到 LocalDateTime
        LocalDate localDate = LocalDate.of(2024, 5, 7);
        LocalTime localTime = LocalTime.of(15,22,34);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime2);
        // 调用LocalDate的atTime方法 实例化LocalDateTime类
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        System.out.println(localDateTime3);
        // 调用LocalTime的atDate方法 实例化LocalDateTime类
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
        System.out.println(localDateTime4);

        // 从LocalDateTime的实例 获取LocalDate
        LocalDate localDate1 = localDateTime.toLocalDate();
        System.out.println(localDate1);
        // 从LocalDateTime实例 获取LocalTime
        LocalTime localTime1 = localDateTime.toLocalTime();
        System.out.println(localTime1);

    }

}
