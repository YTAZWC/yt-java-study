package top.ytazwc.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author 花木凋零成兰
 * @title AtomicIntegerFieldUpdaterTest
 * @date 2024/7/15 11:24
 * @package top.ytazwc.thread.atomic
 * @description 5以AtomicIntegerFieldUpdater为例
 */
public class AtomicIntegerFieldUpdaterTest {

    public static AtomicIntegerFieldUpdater<Person> personAtomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "old");

    public static void main(String[] args) {
        Person person = new Person("ytazwc", 14);
        System.out.println(personAtomicIntegerFieldUpdater.getAndIncrement(person));
        System.out.println(personAtomicIntegerFieldUpdater.get(person));
    }

    static class Person {
        private String name;
        public volatile int old;

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
