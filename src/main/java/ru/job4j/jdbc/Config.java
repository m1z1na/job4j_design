package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
public class Config {

    private final String path;

    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String line;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {

            while ((line = read.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length >= 2 && parts[0] != "" && parts[1] != "") {
                    String key = parts[0];
                    String value = parts[1];
                    values.put(key, value);
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



    public static void main(String[] args) {
        System.out.println(new ru.job4j.io.Config("data/app.properties"));
    }

}