package top.ytazwc.collection.hanshunping.set;

import java.util.HashMap;

/**
 * @author 花木凋零成兰
 * @title HashSetStructure
 * @date 2024-11-14 23:07
 * @package top.ytazwc.collection.hanshunping.set
 * @description
 */
public class HashSetStructure {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
    }


    private final static class MySet<E> {
        @SuppressWarnings({"unchecked"})
        Node<E>[] table = (Node<E>[]) new Node[16];

    }

    private final static class Node<E> {
        E item;
        Node<E> next;
    }

    private final static class Dog<E> {
        E i;
    }

}
