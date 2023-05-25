package ru.job4j.collection;

import java.util.Iterator;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        size++;
        if (head == null) {
            this.head = new Node<>(value, null);
        } else {
            Node<E> currentNode = this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<>(value, null);
        }
    }

    @Override
    public E get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> value = head;
        for (int i = 0; i < index; i++) {
            value = value.next;
        }
        return value.item;
    }

    @Override
    public Iterator<E> iterator() {
        modCount = 0;
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return size > modCount;
            }

            @Override
            public E next() {
                var value = get(modCount);
                modCount++;
                return value;
            }
        };
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}