package top.ytazwc.spi.service.javacore;

import top.ytazwc.spi.javacore.DataStorage;

/**
 * @author 花木凋零成兰
 * @title MysqlStorage
 * @date 2024/5/31 13:41
 * @package top.ytazwc.spi.service.javacore
 * @description MySQL 查询
 */
public class MysqlStorage implements DataStorage {
    /**
     * 使用MySQL搜索对应key值
     * @param key 健
     * @return 返回搜索结果
     */
    @Override
    public String search(String key) {
        return "[MySQL] 搜索" + key + ", 结果: No!";
    }
}
