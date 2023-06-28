package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        size++;
        if (head == null) {
            this.head = new Node<>(value, null);
        } else {
            Node<T> currentNode = this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<>(value, null);
        }
    }

    public T get(int index) {

        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> value = head;
        for (int i = 0; i < index; i++) {
            value = value.next;
        }
        return value.item;
    }


    public T deleteFirst() {
        var value = head.item;

        if (head == null) {
            throw new NoSuchElementException();
        } else {
            head = head.next;
            size--;
        }
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        modCount = 0;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return size > modCount - 1;
            }

            @Override
            public T next() {
//                if (modCount != size) {
//                    throw new ConcurrentModificationException();
//                }
                System.out.println("test");
                var value = get(modCount);
                modCount++;
                return value;
            }
        };
    }

    private static class Node<T> {
        private final T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}