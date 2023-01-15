package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    private final String delimeter = " ";

    public void unavailable(String source, String target) {
        List<String> log = getSource(source);

        for (int i = 0; i < log.size(); i++) {
            System.out.println(log.get(i));
        }
        saveTarget(log, target);

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }


    public void saveTarget(List<String> log, String file) {
        try (FileWriter fw = new FileWriter(file, true)) {

            for (String line : log) {
                fw.write(line);
                fw.write("\n");
            }

        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }


    public List<String> getSource(String file) {

        String lastTime = "";
        List<String> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String beginTime = "";

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] data = line.split(delimeter);
                int code = Integer.parseInt(data[0]);

                if (code >= 400 && code <= 500) {
                    if (beginTime == "") {
                        beginTime = data[1];
                    }
                } else if (beginTime != "") {
                    list.add(beginTime + ";" + data[1] + ";");
                    beginTime = "";
                }

            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}