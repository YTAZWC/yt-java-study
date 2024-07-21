package top.ytazwc.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 花木凋零成兰
 * @title Main
 * @date 2024/7/21 14:00
 * @package top.ytazwc.jvm
 * @description 类加载器示例
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 1. 自定义类加载器
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        //2.利用自定义类加载器加载User类,并生成一个对象实例; selfLoadUser
        Object selfLoadUser = myLoader.loadClass("User").newInstance();
        System.out.println(selfLoadUser.getClass());
        // 3.判断selfLoadUser 是否为User类的实例(注意:这里的User是系统加载器
        System.out.println(selfLoadUser instanceof User);

    }
}
