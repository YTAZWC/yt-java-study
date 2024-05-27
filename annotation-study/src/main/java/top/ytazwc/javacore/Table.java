package top.ytazwc.javacore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author 花木凋零成兰
 * @title Table
 * @date 2024/5/27 11:09
 * @package top.ytazwc.javacore
 * @description @Target 注解示例
 */
@Target(ElementType.TYPE)   // 标记注解可以应用于任何元素
public @interface Table {
    /**
     * 数据表名称注解，默认值为类名称
     * @return
     */
    public String tableName() default "className";
}
