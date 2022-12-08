package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        Box box = new Box(0, 10);
        int name = (int) box.getArea();
        assertThat(name).isEqualTo(1256);

    }


    @Test
    void checkCubeArea() {
        Box box = new Box(8, 5);
        int name = (int) box.getArea();
        assertThat(name).isEqualTo(150);

    }
}