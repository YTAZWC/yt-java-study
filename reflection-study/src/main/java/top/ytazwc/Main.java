package top.ytazwc;

import java.lang.reflect.Field;

/**
 * @author 花木凋零成兰
 * @title Main
 * @date 2024/5/23 20:46
 * @package top.ytazwc
 * @description TODO
 */
public class Main {

    public static void main(String[] args) throws NoSuchFieldException {

        User user = new User();
        Class<?> aClass = user.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        Field comments = aClass.getDeclaredField("comments");

    }

}
