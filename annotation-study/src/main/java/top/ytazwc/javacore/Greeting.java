package top.ytazwc.javacore;

import java.lang.annotation.*;

/**
 * @author 花木凋零成兰
 * @title Greeting
 * @date 2024/5/27 11:18
 * @package top.ytazwc.javacore
 * @description @Inherited
 */
@Inherited
public @interface Greeting {
    public enum FontColor { BLUE, RED, GREEN};
    String name();
    FontColor fonColor() default FontColor.GREEN;
}
