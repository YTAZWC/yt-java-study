package top.ytazwc.spi.service.one;

import top.ytazwc.spi.app.SpiInterfaceService;

/**
 * @author 花木凋零成兰
 * @title SpiOneService
 * @date 2024/5/31 9:54
 * @package top.ytazwc.spi.service
 * @description 实现 spi 提供的接口
 */
public class SpiOneService implements SpiInterfaceService {
    /**
     * 打印参数
     * @param parameter 参数
     */
    @Override
    public void printParameter(String parameter) {
        System.out.println("SpiOneService 打印参数: " + parameter);
    }
}
