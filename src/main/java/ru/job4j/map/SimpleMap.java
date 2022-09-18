package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int i = indexFor(hash(Objects.hashCode(key)));
        boolean rsl = table[i] == null;
        if (rsl) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        if (count >= table.length * LOAD_FACTOR) {
            expand();
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        for (MapEntry mapEntry : table) {
            if (mapEntry != null) {
                int newIndex = indexFor(hash(Objects.hashCode(mapEntry.key)));
                tempTable[newIndex] = mapEntry;
            }
        }
        table = tempTable;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(Objects.hashCode(key)));
        return table[i] != null && Objects.hashCode(table[i].key) == Objects.hashCode(key)
                && Objects.equals(table[i].key, key) ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(Objects.hashCode(key)));
        boolean rsl = table[i] != null && Objects.hashCode(table[i].key) == Objects.hashCode(key)
                && Objects.equals(table[i].key, key);
        if (rsl) {
            table[i] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int current = 0;
            int i = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[i] == null) {
                    i++;
                }
                current++;
                return table[i++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
