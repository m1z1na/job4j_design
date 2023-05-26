package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> currentValue;

    @Override
    public void add(E value) {
        size++;
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<>(value, null);
        }
    }

    @Override
    public E get(int index) {
        if (Objects.checkIndex(index, size) < 0) {
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
        currentValue = head;
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return modCount < size;
            }

            @Override
            public E next() {
                E value = currentValue.item;
                currentValue = currentValue.next;
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