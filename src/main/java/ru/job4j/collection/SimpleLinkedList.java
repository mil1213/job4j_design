package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList {

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
            this.next = null;
        }
    }

    private Node<E> head;

    private int size;

    private int modCount;

    @Override
    public void add(Object value) {
        Node<E> newNode = new Node<>((E) value);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public Object get(int index) {
        Objects.checkIndex(index, size);
        int count = 0;
        Iterator<E> it = iterator();
        while (count != index) {
            it.next();
            count++;
        }
        return it.next();
    }

    @Override
    public Iterator iterator() {

        return new Iterator<E>() {
            int count = 0;
            Node<E> current = head;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                count++;
                E rsl = current.item;
                current = current.next;
                return rsl;
            }
        };
    }
}
