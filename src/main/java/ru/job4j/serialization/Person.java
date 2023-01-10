package ru.job4j.serialization;


import javax.xml.bind.annotation.*;




public class Person  {
    @XmlElement
    private String fio;
    @XmlElement
    private int age;

    public Person(String fio, int age) {
        this.fio = fio;
        this.age = age;
    }
    public Person( ) {

    }
    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", age=" + age +
                '}';
    }




}