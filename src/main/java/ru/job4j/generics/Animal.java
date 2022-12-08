package ru.job4j.generics;

public class Animal extends  TEST{
   private String name;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }

    public Animal( String name){
        super( name );
        this.name = name;
    }

}
