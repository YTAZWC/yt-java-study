package top.ytazwc.spi.service.javacore;

import top.ytazwc.spi.javacore.DataStorage;

/**
 * @author 花木凋零成兰
 * @title RedisStorage
 * @date 2024/5/31 13:42
 * @package top.ytazwc.spi.service.javacore
 * @description Redis 实现接口 查询
 */
public class RedisStorage implements DataStorage {
    /**
     * Redis根据key进行搜索
     * @param key 健
     * @return 搜索结果
     */
    @Override
    public String search(String key) {
        return "[Redis] 搜索" + key + ", 结果: Yes";
    }
}
