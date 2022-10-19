package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptylines() {
        String path = "./data/comment_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan");
        assertThat(config.value("surname")).isEqualTo("Ivanov");
        assertThatThrownBy(() -> config.value("adress"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void whenPairsWithTwoEquals() {
        String path = "./data/pairs_with_two_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan=Ivanov");
        assertThat(config.value("surname")).isEqualTo("Ivanov=");
    }

    @Test
    void whenIncorrectLineKeyValue() {
        String path = "./data/incorrect_lines.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenIncorrectLineKeyOnly() {
        String path = "./data/incorrect_lines_only_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenIncorrectLineValueOnly() {
        String path = "./data/incorrect_lines_only_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenIncorrectLineEqualsOnly() {
        String path = "./data/incorrect_lines_only_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load()).isInstanceOf(IllegalArgumentException.class);
    }


}