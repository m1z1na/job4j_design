package ru.job4j.io;

import java.io.*;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {

            StringBuilder text = new StringBuilder();

            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }


            String[] lines = text.toString().split("\\s+");
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " - это четное число");
                } else {
                    System.out.println(line + " - это нечетное число");
                }
                ;
            }


        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}