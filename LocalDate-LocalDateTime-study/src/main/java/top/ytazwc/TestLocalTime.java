package top.ytazwc;

import java.time.LocalTime;

/**
 * @author 花木凋零成兰
 * @title TestLocalTime
 * @date 2024/5/8 9:59
 * @package top.ytazwc
 * @description 测试LocalTime
 */
public class TestLocalTime {

    public static void main(String[] args) {

        // 获取当前时间
        LocalTime localTime1 = LocalTime.now();
        System.out.println(localTime1);
        // 获取指定时间
        LocalTime localTime2 = LocalTime.of(13, 51, 10);
        // 获取小时
        int hour = localTime1.getHour();
        System.out.println(hour);
        // 获取分
        int minute = localTime1.getMinute();
        System.out.println(minute);
        // 获取秒
        int second = localTime1.getSecond();
        System.out.println(second);

    }

}
