package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;
    private int expectedModCount;


    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        } else {

            if (size == container.length) {
                container = Arrays.copyOf(container, container.length > 0 ? container.length * 2 : 1);
            }
            container[size] = value;
            modCount++;
            expectedModCount++;
            size++;
        }
    }

    @Override
    public T set(int index, T newValue) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T value = container[index];
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        T value;
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        } else if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            value = container[index];
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    container.length - index - 1
            );
        }
        container[container.length - 1] = null;
        size--;
        modCount++;
        expectedModCount++;
        return value;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int sizeI;
        return new Iterator<T>() {
            final int sizeI = size;
            int ind;

            @Override
            public boolean hasNext() {

                if (modCount != expectedModCount || size != modCount || sizeI != size) {
                    throw new ConcurrentModificationException();
                }
                return size > ind;
            }

            @Override
            public T next() {
                if (sizeI != size) {
                    throw new ConcurrentModificationException();
                }
                if (size - 1 < ind) {
                    throw new NoSuchElementException();
                }
                return container[ind++];
            }
        };
    }
}