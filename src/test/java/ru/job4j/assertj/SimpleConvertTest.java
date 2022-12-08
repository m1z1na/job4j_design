package ru.job4j.assertj;


import org.junit.jupiter.api.Test;


class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");

    }
}