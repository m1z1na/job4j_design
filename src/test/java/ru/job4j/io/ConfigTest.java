package ru.job4j.io;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {
    /*комментариями и пустыми строками*/
    @Test
    void whenPairWithCommentAndEmptyRow() {
        String path = "./data/pair_without_comment1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.ignoreLinesExists()).isTrue();
        assertThat(config.value("name")).isEqualTo("Petr");
    }

    /*ключ=значение=1*/
    @Test
    void whenPairWithKeyAndValue() {
        String path = "./data/pair_without_comment4.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("ключ")).isEqualTo("значение=1");
    }


    @Test()
    void whenErrorValue() {
        String path = "./data/pair_without_comment3.properties";
        Config config = new Config(path);
        Throwable throwable = assertThrows(Throwable.class, () -> {
            config.load();
        });
        assertEquals(IllegalArgumentException.class, throwable.getClass());
    }

    @Test
    void whenErrorKey() {
        String path = "./data/pair_without_comment2.properties";
        Config config = new Config(path);
        Throwable throwable = assertThrows(Throwable.class, () -> {
            config.load();
        });
        assertEquals(IllegalArgumentException.class, throwable.getClass());
    }

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr");
    }

    @Test
    void whenSymbol() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("ключ")).isEqualTo("значение=1");
    }

    @Test
    void wheneWithoutignoreLines() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        boolean rsl = config.ignoreLinesExists();
        assertThat(rsl).isFalse();
    }
}