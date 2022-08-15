package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron").doesNotContain("known");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(10, 3);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object").contains("object");
    }

    @Test
    void numberOfVerticesOfSphere() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(0).isZero();
    }

    @Test
    void numberOfVerticesOfUnknown() {
        Box box = new Box(10, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(-1).isNegative();
    }

    @Test
    void whenExist() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue().isEqualTo(true);
    }

    @Test
    void whenIsNotExist() {
        Box box = new Box(10, 10);
        assertThat(box.isExist()).isFalse().isEqualTo(false);
    }

    @Test
    void areaOfTetrahedron() {
        Box box = new Box(4, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(6.93d, withPrecision(0.01d))
                .isCloseTo(6.93, Percentage.withPercentage(1.0d));
    }

    @Test
    void areaOfsphere() {
        Box box = new Box(0, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(113.1d, withPrecision(0.1d))
                .isCloseTo(113.1, Percentage.withPercentage(1.0d));
    }

    @Test
    void areaWhenUnknown() {
        Box box = new Box(10, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(0).isZero();
    }
}