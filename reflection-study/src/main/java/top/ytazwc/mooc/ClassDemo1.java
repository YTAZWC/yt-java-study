package top.ytazwc.mooc;

/**
 * @author 花木凋零成兰
 * @title ClassDemo1
 * @date 2024/5/24 9:25
 * @package top.ytazwc.mooc
 * @description TODO
 */
public class ClassDemo1 {
    public static void main(String[] args) {
        // Foo的实例对象如何表示
        Foo foo1 = new Foo();
        // Foo这个类 本身也是Class类的实例对象
        // 如何表示Class
        // 1. 第一种表示方式 -> 实际是 任何任何一个类都有一个隐藏的静态成员变量class
        Class<Foo> c1 = Foo.class;
        // 2. 第二种表示方式 已知该类对象的实例 通过getClass()方法
        Class<?> c2 = foo1.getClass();
        /*
          c1，c2 表示的foo类的 类 类型(class type)
          万事万物皆对象
          类也是对象 是Class类的实例对象
          这个对象称为 该类的类类型
         */
        // 不管c1还是c2都代表了Foo类的类类型
        // 一个类只可能是Class类的一个实例对象
        System.out.println(c1 == c2);

        // 3. 第三种表示方式
        Class<?> c3 = null;
        try {
            c3 = Class.forName("top.ytazwc.mooc.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
        System.out.println(c2 == c3);
        // 完全可以通过类的类类型创建该类的对象实例 -> 通过c1 or c2 or c3 创建Foo的对象实例
        try {
            // 前提需要有无参数的构造方法
            Foo foo = c1.newInstance();
            foo.print();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace(System.out);
        }
    }
}

class Foo {
    void print() {
        System.out.println("Foo print ...");
    }
}

