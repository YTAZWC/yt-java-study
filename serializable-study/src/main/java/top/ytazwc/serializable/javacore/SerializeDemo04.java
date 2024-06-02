package top.ytazwc.serializable.javacore;

import java.io.*;
import java.nio.file.Files;

/**
 * @author 花木凋零成兰
 * @title SerializeDemo04
 * @date 2024/6/2 19:51
 * @package top.ytazwc.serializable.javacore
 * @description readResolve() 方法
 */
public class SerializeDemo04 {

    enum Sex {
        MALE, FEMALE
    }

    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        transient private Integer age;
        private Sex sex;
        static final Person instance = new Person("Tom", 31, Sex.MALE);

        private Person() {
        }

        private Person(String name, Integer age, Sex sex) {
             this.name = name;
             this.age = age;
             this.sex = sex;
        }

        public static Person getInstance() {
            return instance;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeInt(age);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            age = in.readInt();
        }

        private Object readResolve() {
            return instance;
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
    // 序列化
    private static void serialize(String filename) throws IOException {
        // 定义保存路径
        File file = new File(filename);
        // 文件输出流
        OutputStream outputStream = Files.newOutputStream(file.toPath());
        // 对象输出流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        // 保存对象
        objectOutputStream.writeObject(new Person("ytazwc", 21, Sex.MALE));
        // 关闭资源
        objectOutputStream.close();
        outputStream.close();
    }
    private static void deserialize(String filename) throws IOException, ClassNotFoundException {
        // 定义文件路径
        File file = new File(filename);
        // 文件输入流
        InputStream inputStream = Files.newInputStream(file.toPath());
        // 对象输入流
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        // 读取对象
        Object object = objectInputStream.readObject();
        System.out.println(object);
        System.out.println(object == Person.getInstance());
        // 关闭资源
        objectInputStream.close();
        inputStream.close();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = "E:/workspace/Java/yt-java-study/serializable-study/Data/text5.dat";
        serialize(filename);
        deserialize(filename);
    }
}
