package top.ytazwc.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 花木凋零成兰
 * @title AtomicReferenceTest
 * @date 2024/7/15 11:18
 * @package top.ytazwc.thread.atomic
 * @description AtomicReference举例
 */
public class AtomicReferenceTest {

    public static AtomicReference<Person> atomicUserRef = new AtomicReference<>();

    public static void main(String[] args) {
        Person person = new Person("yt", 12);
        atomicUserRef.set(person);
        Person updateUser = new Person("zwc", 16);
        atomicUserRef.compareAndSet(person, updateUser);
        Person nowPerson = atomicUserRef.get();
        System.out.println(nowPerson.getName());
        System.out.println(nowPerson.getOld());
    }

    static class Person {
        private String name;
        private int old;

        public Person(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
