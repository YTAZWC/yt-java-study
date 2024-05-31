package top.ytazwc.spi.service.two;

import top.ytazwc.spi.app.SpiInterfaceService;

/**
 * @author 花木凋零成兰
 * @title SpiTwoService
 * @date 2024/5/31 10:00
 * @package top.ytazwc.spi.service.two
 * @description 实现 spi 提供的接口
 */
public class SpiTwoService implements SpiInterfaceService {
    /**
     * 打印参数
     * @param parameter 参数
     */
    @Override
    public void printParameter(String parameter) {
        System.out.println("SpiTwoService 打印参数: " + parameter);
    }
}
