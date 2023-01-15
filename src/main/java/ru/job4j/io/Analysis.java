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


    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }


    public void saveTarget() {
      /*  try {
            FileWriter fw = new FileWriter(file, true);
            for (int i = 0; i < log.size(); i++) {
                fw.write(log.get(i));
                fw.write("\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }*/
    }


    public List<String> getSource(String file) {
        boolean closeTime = false;
        String lastTime = "";
        List<String> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String period = "";
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] data = line.split(delimeter);
                int code = Integer.parseInt(data[0]);
                lastTime = data[1];
                if (code >= 400 && code <= 500) {
                    if (closeTime == true) {
                        period = period + ";" + data[1] + ";";
                        list.add(period);
                        closeTime = false;
                    } else {
                        closeTime = true;
                        period = data[1];
                    }
                }
            }
            if (closeTime == true) {
                period = period + ";" + lastTime + ";";
                list.add(period);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}