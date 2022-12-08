package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin<T> {


    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

    }

    public void max (List<T> value, Comparator<T> comparator) {

        for (T val:value ) {
            
                System.out.println(val);

            }
 
    }


        public <T> T min(List<T> values, Comparator<T> comparator) {
            return null;
        }
    }



