package top.ytazwc.spi.app.javacore;

import top.ytazwc.spi.javacore.DataStorage;

import java.util.ServiceLoader;

/**
 * @author 花木凋零成兰
 * @title SpiDemo
 * @date 2024/5/31 13:46
 * @package top.ytazwc.spi.javacore
 * @description 通过 ServiceLoader 来加载服务
 */
public class SpiDemo {
    public static void main(String[] args) {
        ServiceLoader<DataStorage> serviceLoader = ServiceLoader.load(DataStorage.class);
        System.out.println("========== Java SPI 测试 ==========");
        serviceLoader.forEach(loader -> System.out.println(loader.search("Yes Or No")));
    }
}
