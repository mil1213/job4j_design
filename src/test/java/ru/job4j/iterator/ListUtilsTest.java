package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenPredicateRemoveIf() {
        ListUtils.addBefore(input, 1, 2);
        Predicate<Integer> isEvenNumber = x -> x % 2 == 0;
        ListUtils.removeIf(input, isEvenNumber);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenPredicateReplaceIf() {
        ListUtils.addBefore(input, 1, 2);
        Predicate<Integer> isEvenNumber = x -> x % 2 == 0;
        ListUtils.replaceIf(input, isEvenNumber, 7);
        assertThat(input).hasSize(3).containsSequence(1, 7, 3);
    }

    @Test
    void whenRemoveAllOfList() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        List<Integer> elements = List.of(2, 4, 5);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }
}