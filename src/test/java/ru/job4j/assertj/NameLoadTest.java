package ru.job4j.assertj;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkIllegalArgumentExceptionEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("Names array is empty");
    }


    @Test
    void checkIllegalArgumentExceptionValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("1="))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("this name: 1= does not contain a value");
    }

    @Test
    void checkIllegalArgumentExceptionSplit() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(() -> nameLoad.parse("1"))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("does not contain the symbol '='");
    }

    @Test
    void checkIllegalArgumentExceptionKey() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(() -> nameLoad.parse("=3"))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("does not contain a key");
    }
}

