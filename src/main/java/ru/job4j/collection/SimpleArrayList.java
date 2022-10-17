package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;
    private Object T;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {

    }

    @Override
    public T set(int index, T newValue) {
    return (T) T;
    }

    @Override
    public T remove(int index) {
        return (T) T;
    }

    @Override
    public T get(int index) {
        return (T) T;
    }

    @Override
    public int size() {
        return (int) T;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return (boolean) T;
            }

            @Override
            public T next() {
                return (T) T;
            }

        };
    }
}