package top.ytazwc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 花木凋零成兰
 * @title MyAnnotation
 * @date 2024/5/17 22:54
 * @package top.ytazwc.annotation
 * @description 自定义注解
 */
// 指定注解的保留策略和目标
@Retention(RetentionPolicy.RUNTIME) // 在运行时保留注解
@Target(ElementType.METHOD) // 该注解可以应用于方法
public @interface MyAnnotation {
}
