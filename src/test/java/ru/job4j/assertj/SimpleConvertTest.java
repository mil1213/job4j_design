package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .containsExactlyInAnyOrder("second", "first", "four", "three", "five")
                .doesNotContain("seven");
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotEmpty()
                .containsExactly("first", "second", "three", "four", "five")
                .anySatisfy(e -> assertThat(e).endsWith("st"))
                .anyMatch(e -> e == "three");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(1).isEqualTo("second");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("apple", "approve", "love");
        assertThat(set).isNotNull()
                .containsExactlyInAnyOrder("approve", "apple", "love")
                .anySatisfy(e -> assertThat(e).startsWith("app"));
        assertThat(set).filteredOnAssertions(e -> assertThat(e).contains("ove"))
                .hasSize(2);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("apple", "approve", "love", "apple");
        assertThat(map).hasSize(3)
                .doesNotContainKey("drive")
                .doesNotContainValue(3)
                .containsValue(1)
                .containsEntry("apple", 0);
    }
}