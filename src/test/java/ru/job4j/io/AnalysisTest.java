package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class AnalysisTest {

    @Test
    void saveTest(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();

        List<String> log = analysis.getSource("data/server.log");
        File target = tempDir.resolve("targetTest.txt").toFile();
        try (PrintWriter out = new PrintWriter(target)) {
            for (int i = 0; i < log.size(); i++) {
                out.println(log.get(i));
            }
        }

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;11:02:02;").isEqualTo(rsl.toString());
    }
}