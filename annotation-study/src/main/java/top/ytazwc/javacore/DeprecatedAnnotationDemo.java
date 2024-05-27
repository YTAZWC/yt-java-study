package top.ytazwc.javacore;

/**
 * @author 花木凋零成兰
 * @title DeprecatedAnnotationDemo
 * @date 2024/5/27 9:02
 * @package top.ytazwc.javacore
 * @description Deprecated 注解；用于标明被修饰的类或类成员、类方法已经废弃、过时，不建议使用。
 * Deprecated 有一定的延续性，在代码中通过继承或覆盖的方式使用过时的类或类成员
 * 即使子类没有标记为 @Deprecated 编译器仍然会告警
 */
public class DeprecatedAnnotationDemo {
    static class DeprecatedField {
        @Deprecated
        public static final String DEPRECATED_FIELD = "DeprecatedField";
    }
    static class DeprecatedMethod {
        @Deprecated
        public String print() {
            return "DeprecatedMethod";
        }
    }
    @Deprecated
    static class DeprecatedClass {
        public String print() {
            return "DeprecatedClass";
        }
    }

    public static void main(String[] args) {
        System.out.println(DeprecatedField.DEPRECATED_FIELD);

        DeprecatedMethod dm = new DeprecatedMethod();
        System.out.println(dm.print());

        DeprecatedClass dc = new DeprecatedClass();
        System.out.println(dc.print());
    }

}
