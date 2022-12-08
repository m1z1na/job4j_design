package ru.job4j.assertj;


import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));

    }


    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList("first", "second", "second", "three", "four", "five");
        assertThat(array).hasSize(6)
                .contains("second")
                .contains("second", Index.atIndex(2));

    }


    @Test
    void checktoSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first","second", "second", "three", "four", "five");
        assertThat(set).containsOnlyOnce("second")
                       .hasSize(5);
    }


    @Test
    void checktoMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first","second", "second", "three", "four", "five");
        assertThat(map)
                .containsOnlyKeys("first", "second", "three", "four", "five")
                .containsKey("first");

    }
}