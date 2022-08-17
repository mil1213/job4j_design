package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void namesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void nameNotContainsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] name = {"001PetrovP"};
        assertThatThrownBy(() -> nameLoad.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the symbol \"=\"");
    }

    @Test
    void nameNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String[] name = {"=PetrovP"};
        assertThatThrownBy(() -> nameLoad.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a key");
    }

    @Test
    void nameNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String[] name = {"001="};
        assertThatThrownBy(() -> nameLoad.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a value");
    }

}