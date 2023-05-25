package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {

        if (size == container.length) {
            container = changeCapacity();
        }
        container[size++] = value;
        modCount++;
    }


    @Override
    public T set(int index, T newValue) {
        if (Objects.checkIndex(index, size) < 0) {
            throw new IndexOutOfBoundsException();
        }
        T value = container[index];
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        T value;
        if (Objects.checkIndex(index, size) < 0) {
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
        return value;
    }

    @Override
    public T get(int index) {
        if (Objects.checkIndex(index, size) < 0) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }


    private T[] changeCapacity() {
        return Arrays.copyOf(container, container.length > 0 ? container.length * 2 : 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int sizeI = modCount;
            int ind;

            @Override
            public boolean hasNext() {

                if (modCount != size ||  sizeI != size) {
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