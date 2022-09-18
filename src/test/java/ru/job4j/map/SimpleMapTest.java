package ru.job4j.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SimpleMapTest {
    private final SimpleMap<Integer, String> map = new SimpleMap<>();

    @BeforeEach
    void setUp() {
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
    }

    @Test
    void checkSimpleIterator() {
        assertThat(map).hasSize(4).contains(1, 2, 3, 4);
    }

    @Test
    void whenCheckGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(4);
        assertThat(map.get(5)).isNull();
        assertThat(map).hasSize(4);
    }

    @Test
    void whenCheckPut() {
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(8, "8")).isFalse();
        assertThat(map).hasSize(5);
        assertThat(map.put(1, "10")).isFalse();
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemove() {
        assertThat(map.remove(2)).isTrue();
        assertThat(map).hasSize(3);
        assertThat(map.remove(2)).isFalse();
        assertThat(map).hasSize(3);
        assertThat(map.remove(5)).isFalse();
        assertThat(map).hasSize(3);
    }

    @Test
    void whenCheckIterator() {
        map.remove(2);
        map.remove(3);
        map.put(null, "0000");
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isNull();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isFalse();
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenConcurrentIteratorAdd() {
        Iterator<Integer> it = map.iterator();
        map.put(0, "0");
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenConcurrentIteratorRemove() {
        Iterator<Integer> it = map.iterator();
        map.remove(1);
        assertThatThrownBy(it::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenNotConcurrentIteratorGet() {
        Iterator<Integer> it = map.iterator();
        map.get(1);
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenMapExpand() {
        map.put(null, "0000");
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(6);
        assertThat(map.put(8, "8")).isTrue();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.get(4)).isEqualTo("4");
        assertThat(map.get(8)).isEqualTo("8");
        assertThat(map.get(15)).isEqualTo("15");
        assertThat(map).hasSize(7).contains(null, 1, 2, 3, 4, 8, 15);
    }

    @Test
    void whenCheckPutKeyNull() {
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckGetKeyNull() {
        map.put(null, "0000");
        assertThat(map.get(null)).isEqualTo("0000");
        assertThat(map).hasSize(5);
    }

    @Test
    void whenCheckRemoveKeyNull() {
        map.put(null, "0000");
        assertThat(map.remove(null)).isTrue();
        assertThat(map).hasSize(4);
    }


    @Test
    void whenCheckPutZeroAndNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.put(0, "0")).isFalse();
    }

    @Test
    void whenCheckPutNullAndZero() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.put(null, "0000")).isFalse();
    }

    @Test
    void whenCheckGetZeroAndNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(null, "0000")).isTrue();
        assertThat(map.get(0)).isNull();
    }

    @Test
    void whenCheckGetNullAndZero() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.put(0, "0")).isTrue();
        assertThat(map.get(null)).isNull();
    }

    @Test
    void whenPutUniqueKey() {
        assertThat(map.put(5, "5")).isTrue();
        assertThat(map.put(13, "13")).isFalse();
        assertThat(map.put(6, "6")).isTrue();
        assertThat(map.put(15, "15")).isTrue();
        assertThat(map).hasSize(7);
        assertThat(map.put(31, "31")).isFalse();
        assertThat(map.get(31)).isNull();
        assertThat(map).contains(1, 2, 3, 4, 5, 6, 15);
    }

    @Test
    void whenPutDuplicateKey() {
        assertThat(map.put(1, "001")).isFalse();
        assertThat(map).hasSize(4);
        assertThat(map.put(2, "002")).isFalse();
        assertThat(map).hasSize(4);
        assertThat(map.put(null, "000")).isTrue();
        assertThat(map).hasSize(5);
        assertThat(map.put(0, "00000")).isFalse();
        assertThat(map.get(null)).isEqualTo("000");
        assertThat(map).contains(null, 1, 2, 3, 4);
    }

    @Test
    void whenGet() {
        assertThat(map.get(1)).isEqualTo("1");
        assertThat(map.get(5)).isEqualTo(null);
    }

    @Test
    void whenPutfndGetNull() {
        assertThat(map.put(null, "000")).isTrue();
        assertThat(map.get(null)).isEqualTo("000");
        assertThat(map.put(null, "00000")).isFalse();
    }

    @Test
    void whenRemove() {
        assertThat(map.remove(1)).isTrue();
        assertThat(map.get(1)).isNull();
        assertThat(map).contains(2, 3, 4);
    }

    @Test
    void whenRemoveNotExisting() {
        assertThat(map.put(null, "000")).isTrue();
        assertThat(map.remove(0)).isFalse();
        assertThat(map.put(16, "16")).isFalse();
        assertThat(map.remove(16)).isFalse();
        assertThat(map).contains(null, 1, 2, 3, 4);
    }

    @Test
    void whenIterator() {
        assertThat(map.put(5, "5")).isTrue();
        assertThat(map.put(6, "6")).isTrue();
        assertThat(map.put(15, "15")).isTrue();
        Iterator<Integer> iter = map.iterator();
        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.next()).isEqualTo(1);
        assertThat(iter.next()).isEqualTo(2);
        assertThat(iter.next()).isEqualTo(3);
        assertThat(iter.next()).isEqualTo(4);
        assertThat(iter.next()).isEqualTo(5);
        assertThat(iter.next()).isEqualTo(6);
        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.next()).isEqualTo(15);
        assertThatThrownBy(iter::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenIteratorHasException() {
        Iterator<Integer> iter1 = map.iterator();
        assertThat(iter1.hasNext()).isTrue();
        assertThat(iter1.next()).isEqualTo(1);
        assertThat(map.remove(2)).isTrue();
        assertThatThrownBy(iter1::next)
                .isInstanceOf(ConcurrentModificationException.class);
        Iterator<Integer> iter2 = map.iterator();
        assertThat(iter2.next()).isEqualTo(1);
        assertThat(iter2.next()).isEqualTo(3);
        assertThat(map.put(2, "2")).isTrue();
        assertThatThrownBy(iter2::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }
}