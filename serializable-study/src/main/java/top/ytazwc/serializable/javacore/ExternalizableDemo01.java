package top.ytazwc.serializable.javacore;

import java.io.*;
import java.nio.file.Files;

/**
 * @author 花木凋零成兰
 * @title ExternalizableDemo01
 * @date 2024/6/1 13:49
 * @package top.ytazwc.serializable.javacore
 * @description 测试 Externalizable 接口
 */
public class ExternalizableDemo01 {

    enum Sex {
        MALE,
        FEMALE
    }

    static class Person implements Externalizable {

        transient private Integer age;
        private String name;
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

        private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
            out.defaultWriteObject();
            out.writeInt(age);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            age = in.readInt();
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {

        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

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
        oos.writeObject(new SerializeDemo02.Person("Jack", 30, SerializeDemo02.Sex.MALE));

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
        final String fileName = "E:/workspace/yt-java-study/serializable-study/Data/text2.dat";
        serialize(fileName);
        deSerialize(fileName);
    }

}
