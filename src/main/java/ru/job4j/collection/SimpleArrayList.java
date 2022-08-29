package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = Arrays.copyOf(container, size * 2);
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size());
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size());
        modCount++;
        T removedValue = container[index];
        int newSize = size - 1;
        if (index < newSize) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
        }
        container[newSize] = null;
        size = newSize;
        return removedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size());
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int count = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}
