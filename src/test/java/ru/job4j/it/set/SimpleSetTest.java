package ru.job4j.it.set;


import org.junit.jupiter.api.Test;
import ru.job4j.set.SimpleSet;

import java.util.Set;



class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
         set.add(1);
        set.contains(1);
        set.add(1);
    }



    @Test
    void whenAddNull() {
//        Set<Integer> set = new SimpleSet<>();
//        assertThat(set.add(null)).isTrue();
//        assertThat(set.contains(null)).isTrue();
//        assertThat(set.add(null)).isFalse();
    }
}