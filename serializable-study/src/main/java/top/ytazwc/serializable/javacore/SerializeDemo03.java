package top.ytazwc.serializable.javacore;

import java.io.*;
import java.nio.file.Files;

/**
 * @author 花木凋零成兰
 * @title SerializeDemo03
 * @date 2024/6/2 16:52
 * @package top.ytazwc.serializable.javacore
 * @description Externalizable替代方法
 */
public class SerializeDemo03 {
    enum Sex {
        MALE,
        FEMALE
    }
    static class Person implements Serializable {

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

        private void writeObject(ObjectOutputStream out) throws IOException {
            System.out.println("默认调用 writeObject ...");
            out.defaultWriteObject();
            out.writeInt(age);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            System.out.println("默认调用 readObject ...");
            in.defaultReadObject();
            age = in.readInt();
        }

    }
    // 序列化
    private static void serialize(String fileName) throws IOException {
        File file = new File(fileName);
        // 文件输出流
        OutputStream outputStream = Files.newOutputStream(file.toPath());
        // 类输出
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        // 将类序列化后写入文件中
        objectOutputStream.writeObject(new Person("ytazwc", 21, Sex.MALE));

        // 关闭资源
        objectOutputStream.close();
        outputStream.close();
    }
    // 反序列化
    private static void deserialize(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        // 文件输入流
        InputStream inputStream = Files.newInputStream(file.toPath());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Person person = (Person)objectInputStream.readObject();
        System.out.println(person);

        // 关闭资源
        objectInputStream.close();
        inputStream.close();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = "E:/workspace/Java/yt-java-study/serializable-study/Data/text3.dat";
        serialize(filename);
        deserialize(filename);
    }

}
