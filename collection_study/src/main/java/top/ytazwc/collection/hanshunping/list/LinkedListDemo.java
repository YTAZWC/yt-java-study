package top.ytazwc.collection.hanshunping.list;

import lombok.Data;

/**
 * @author 花木凋零成兰
 * @title LinkedListDemo
 * @date 2024-11-13 22:50
 * @package top.ytazwc.collection.hanshunping.list
 * @description
 */
public class LinkedListDemo<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    @Data
    private static final class Node<E> {
        private Node<E> prev;
        private Node<E> next;
        E value;

        public Node() {
        }

        public Node(Node<E> prev, Node<E> next, E value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
}
