package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class Matrix {
    public static void main(String[] args) {
        var list =  Matrix.multiple(10);
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write(Arrays.deepToString(list).getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
