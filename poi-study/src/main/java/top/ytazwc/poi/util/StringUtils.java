package top.ytazwc.poi.util;

/**
 * @author yt
 * 2024/7/1
 */
public class StringUtils {
    public static final String SPACE = " ";
    public static final String EMPTY = "";

    /**
     * 去除全部的空格
     */
    public static String getStrAllTrim(Object obj) {
        return getStrAllTrim(String.valueOf(obj));
    }

    /**
     * 去除全部的空格
     */
    public static String getStrAllTrim(String obj) {
        return obj.replaceAll(SPACE, EMPTY);
    }

}
