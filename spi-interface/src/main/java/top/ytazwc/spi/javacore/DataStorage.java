package top.ytazwc.spi.javacore;

/**
 * @author 花木凋零成兰
 * @title DataStorage
 * @date 2024/5/31 13:35
 * @package top.ytazwc.spi.app
 * @description 数据存储接口
 */
public interface DataStorage {
    /**
     * 根据key进行搜索
     * @param key 健
     * @return  搜索结果
     */
    String search(String key);
}
