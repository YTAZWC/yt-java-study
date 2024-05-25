package top.ytazwc.guide;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 花木凋零成兰
 * @title Main
 * @date 2024/5/24 8:45
 * @package top.ytazwc.guide
 * @description 主函数
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        // 获取TargetObject类的Class对象 并创建TargetObject实例
        Class<?> targetClass = Class.forName("top.ytazwc.guide.TargetObject");
        Object object = targetClass.newInstance();
        // 已知具体类的情况下可以进行强转
        TargetObject targetObject = (TargetObject) object;
        // 但是反射的应用场景 更多是不知道具体类的情况

        // 获取TargetObject类中定义的所有方法
        Method[] methods = targetClass.getDeclaredMethods();
        // 遍历方法 并输出每个方法的名字
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // 获取指定的方法进行调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(object, "zwc");

        // 获取指定参数进行修改
        Field field = targetClass.getDeclaredField("value");
        // 私有变量需要设置访问权限 取消安全检查
        field.setAccessible(true);
        // 修改前 将旧值输出
        System.out.println(field.get(object));
        field.set(object, "Good!");
        // 修改后 将新值输出
        System.out.println(field.get(object));

        // 使用private方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        // 取消私有方法的安全检查 设置访问权限
        privateMethod.setAccessible(true);
        // 执行
        privateMethod.invoke(object);

    }

}
