package ru.job4j.serialization;


import javax.xml.bind.annotation.*;


@XmlRootElement(name = "person")
public class Person {
    @XmlAttribute
    private final String fio;
    @XmlAttribute
    private final int age;

    public Person(String fio, int age) {
        this.fio = fio;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", age=" + age +
                '}';
    }
}