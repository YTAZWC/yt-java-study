package top.ytazwc.mooc;

import java.lang.annotation.*;

/**
 * @author 花木凋零成兰
 * @title Description
 * @date 2024/5/27 15:46
 * @package top.ytazwc.mooc
 * @description TODO
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
}
