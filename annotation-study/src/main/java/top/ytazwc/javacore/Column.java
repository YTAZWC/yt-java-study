package top.ytazwc.javacore;

import java.lang.annotation.*;

/**
 * @author 花木凋零成兰
 * @title Column
 * @date 2024/5/27 10:52
 * @package top.ytazwc.javacore
 * @description @Retention 示例
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    public String name() default "filedName";
    public String setFuncName() default "setField";
    public String getFuncName() default "getField";
    public boolean defaultDBValue() default false;
}
