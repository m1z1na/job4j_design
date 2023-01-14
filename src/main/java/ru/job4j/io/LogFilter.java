package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String delimeter = " ";
    private final String error = "404";

    public List<String> filter(String file) {

        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] data = line.split(delimeter);
                if (data[data.length - 2].equals(error)) {
                    String oneLine = "";
                    for (int i = 0; i < data.length - 2; i++) {
                        oneLine = oneLine + " " + data[i];
                    }
                    list.add(oneLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");

        for (int i = 0; i < log.size(); i++) {
            System.out.println(log.get(i));
        }
    }
    public static void save(List<String> log, String file) {

    }
}