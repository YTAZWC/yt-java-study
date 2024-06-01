package top.ytazwc.serializable.javacore;

import java.io.*;

/**
 * @author 花木凋零成兰
 * @title ExternalizableDemo01
 * @date 2024/6/1 13:49
 * @package top.ytazwc.serializable.javacore
 * @description 测试 Externalizable 接口
 */
public class ExternalizableDemo01 {
    static class Person implements Externalizable {

        transient private Integer age;

        private void writeObject(ObjectInputStream out) {

        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {

        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        }
    }
}
