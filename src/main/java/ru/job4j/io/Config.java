package ru.job4j.io;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private int ignoreLine;

    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String line;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {

            while ((line = read.readLine()) != null ) {
                String[] parts = line.length() > 0 ? line.split("=", 2) : null;
                if ( parts != null && !parts[0].isBlank() && !parts[1].isBlank()) {
                    String key = parts[0];
                    String value = parts[1];
                    values.put(key, value);
                } else if ( parts == null || line.contains("#") ) {
                    ignoreLine = ignoreLine + 1;
                } else {
                   throw new IllegalArgumentException();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public boolean ignoreLinesExists() {
        return ignoreLine > 0;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}