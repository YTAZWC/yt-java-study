package top.ytazwc.javacore;

/**
 * @author 花木凋零成兰
 * @title SuppressWarningAnnotationDemo
 * @date 2024/5/27 9:27
 * @package top.ytazwc.javacore
 * @description 用于关闭对类、方法、成员编译时产生的特定警告
 * 内含 String[] 数组成员，存储要关闭的告警类型
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SuppressWarningAnnotationDemo {

    static class SuppressDemo<T> {
        private T value;

        public T getValue() {
            return this.value;
        }
        public void setValue(T value) {
            this.value = value;
        }
    }

    @SuppressWarnings({"deprecation"})
    public static void main(String[] args) {
        SuppressDemo d = new SuppressDemo();
        d.setValue("南京");
        System.out.println("地名: " + d.getValue());
    }

}
