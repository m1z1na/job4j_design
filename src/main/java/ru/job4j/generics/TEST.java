package ru.job4j.generics;

public class TEST {
        private String name;

        @Override
        public String toString() {
            return "TEST{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public TEST( String name){
            this.name = name;
        }
    }
