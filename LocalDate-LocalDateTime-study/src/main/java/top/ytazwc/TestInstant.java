package top.ytazwc;

import java.time.Instant;

/**
 * @author 花木凋零成兰
 * @title TestInstant
 * @date 2024/5/8 10:42
 * @package top.ytazwc
 * @description 测试Instant
 */
public class TestInstant {

    public static void main(String[] args) {

        // 时间是 自1970年1月1日00:00:00 UTC 计算
        Instant instant = Instant.now();
        // 获取当前 秒数
        System.out.println(instant.getEpochSecond());
        // 获取当前 毫秒
        System.out.println(instant.toEpochMilli());

        // 获取 毫秒
        // 在获取毫秒数或秒上 System.currentTimeMillis() 可能更为方便
        System.out.println(System.currentTimeMillis());

    }

}
