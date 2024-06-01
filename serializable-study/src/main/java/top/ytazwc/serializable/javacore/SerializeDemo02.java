package top.ytazwc.serializable.javacore;

import java.io.*;
import java.nio.file.Files;

/**
 * @author 花木凋零成兰
 * @title SerializeDemo01
 * @date 2024/5/31 17:46
 * @package top.ytazwc.serializable.javacore
 * @description 序列化时，默认序列化机制会忽略被声明为 transient 的字段
 */
public class SerializeDemo02 {
    enum Sex {
        MALE,
        FEMALE
    }

    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        transient private Integer age;
        private Sex sex;

        public Person() {

        }

        public Person(String name, Integer age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }
    }

    /**
     * 序列化
     * @param fileName 序列化流保存路径
     * @throws IOException 异常
     */
    private static void serialize(String fileName) throws IOException {
        // 定义保存路径
        File file = new File(fileName);
        // 文件输出流
        OutputStream out = Files.newOutputStream(file.toPath());
        // 对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(out);
        // 保存对象
        oos.writeObject(new Person("Jack", 30, Sex.MALE));

        // 关闭资源
        oos.close();
        out.close();
    }

    private static void deSerialize(String fileName) throws IOException, ClassNotFoundException {
        // 文件保存路径
        File file = new File(fileName);
        // 文件输入流
        InputStream in = Files.newInputStream(file.toPath());
        // 对象输入流
        ObjectInputStream ois = new ObjectInputStream(in);
        // 读取对象
        Object obj = ois.readObject();
        // 关闭资源
        ois.close();
        in.close();

        // 输出读取的对象
        System.out.println(obj);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final String fileName = "E:/workspace/Java/yt-java-study/serializable-study/Data/text.dat";
        serialize(fileName);
        deSerialize(fileName);
    }

}
