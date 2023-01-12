package ru.job4j.io;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr");
    }

    @Test
    void whenerrorLinesExists() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        boolean rsl = config.errorLinesExists();
        assertThat(rsl).isTrue();
    }

    @Test
    void wheneignoreLinesExists() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        boolean rsl = config.ignoreLinesExists();
        assertThat(rsl).isTrue();
    }
}