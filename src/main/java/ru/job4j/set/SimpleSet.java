package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        int prev = set.size();
        if (!contains(value)) {
            set.add(value);
        }
        return set.size() == prev + 1;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
