package top.ytazwc.spi.app.test;

import top.ytazwc.spi.app.SpiInterfaceService;

import java.util.ServiceLoader;

/**
 * @author 花木凋零成兰
 * @title SpiService
 * @date 2024/5/31 10:05
 * @package top.ytazwc.spi
 * @description 测试spi服务
 */
public class SpiService {
    public static void main(String[] args) {
        ServiceLoader<SpiInterfaceService> spiInterfaceServices = ServiceLoader.load(SpiInterfaceService.class);
        for (SpiInterfaceService spi : spiInterfaceServices) {
            spi.printParameter("parameter");
        }
    }
}
