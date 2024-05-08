package top.ytazwc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 花木凋零成兰
 * @title TestCommon
 * @date 2024/5/8 10:56
 * @package top.ytazwc
 * @description 测试LocalDate、LocalTime、LocalDateTime、Instant的不可变
 */
public class TestImmutable {

    public static void main(String[] args) {

        // 指定时间
        LocalDateTime localDateTime = LocalDateTime.of(2023, 6, 24, 5, 2, 1);
        System.out.println(localDateTime);
        // 增加一年
        LocalDateTime localDateTime1 = localDateTime.plusYears(1);
        System.out.println(localDateTime1);
        // 减少一个月
        LocalDateTime localDateTime2 = localDateTime.minusMonths(1);
        System.out.println(localDateTime2);
        // 增加一天
        LocalDateTime localDateTime3 = localDateTime1.plusDays(1);
        System.out.println(localDateTime3);

        // 修改年为2022
        LocalDateTime localDateTime4 = localDateTime.withYear(2022);
        System.out.println(localDateTime4);

        // 对时间进行格式化
        LocalDate localDate = LocalDate.of(2024, 6, 24);
        System.out.println(localDate);
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s2);

        // 自定义格式化样式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s = localDate.format(dateTimeFormatter);
        System.out.println(s);

        // 解析对应格式时间
        LocalDate localDate1 = LocalDate.parse("20240624", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate1);
        LocalDate localDate2 = LocalDate.parse("2024-06-24", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate2);
        LocalDate localDate3 = LocalDate.parse("24/06/2024", dateTimeFormatter);
        System.out.println(localDate3);

    }

}
