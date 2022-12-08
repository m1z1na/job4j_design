package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }


    @Test
    void checkNumberOfVertices() {
        Box box = new Box(8, 5);
        int line = box.getNumberOfVertices();
        assertThat(line).isEqualTo(8);
    }

    @Test
    void checkIsExist() {
        Box box = new Box(8, 5);
        assertThat(box.isExist()).isEqualTo(true);
    }

    @Test
    void checkIsNotExist() {
        Box box = new Box(9, 9);
        assertThat(box.isExist()).isEqualTo(false);
    }


    @Test
    void checkSphereArea() {
        Box box = new Box(0, 5);
        double name = box.getArea();
        assertThat(name).isEqualTo(314.16, withPrecision(0.5d));

    }


    @Test
    void checkCubeArea() {
        Box box = new Box(8, 1);
        double name = box.getArea();
        assertThat(name).isEqualTo(6.0, withPrecision(2d));
    }
}