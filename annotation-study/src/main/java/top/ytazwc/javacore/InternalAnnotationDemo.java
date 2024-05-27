package top.ytazwc.javacore;

/**
 * @author 花木凋零成兰
 * @title InternalAnnotationDemo
 * @date 2024/5/27 9:36
 * @package top.ytazwc.javacore
 * @description TODO
 */
@SuppressWarnings({"deprecation"})
public class InternalAnnotationDemo {

    static class A {
        public void method1() {
            System.out.println("call method1");
        }

        /**
         * @Deprecated 标记当前方法为废弃方法，不建议使用
         */
        @Deprecated
        public void method2() {
            System.out.println("call method2");
        }
    }

    @Deprecated // 标记当前类为废弃类，不建议使用
    static class B extends A {
        /**
         * @Override 标记显示指明当前方法覆写了父类或接口的方法
         */
        @Override
        public void method1() {
        }
    }

    public static void main(String[] args) {
        A obj = new B();
        obj.method1();
        obj.method2();
    }

}
