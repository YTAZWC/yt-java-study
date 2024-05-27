package top.ytazwc.mooc;

/**
 * @author 花木凋零成兰
 * @title Person
 * @date 2024/5/27 15:15
 * @package top.ytazwc.mooc
 * @description 人类接口
 */
public interface Person {
    public String name();
    public int age();
    @Deprecated
    public void sing();
}
