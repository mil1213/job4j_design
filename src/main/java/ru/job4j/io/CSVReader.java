package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String[] filter = argsName.get("filter").split(",");
        Scanner scanner = new Scanner(new File(argsName.get("path")));
        String[] firstLine = scanner.nextLine().split(argsName.get("delimiter"));
        int[] column = new int[filter.length];

        for (int j = 0; j < column.length; j++) {
            for (int i = 0; i < firstLine.length; i++) {
                if (filter[j].equals(firstLine[i])) {
                    column[j] = i;
                }
            }
        }
        List<String> sortedTab = new ArrayList<>();
        sortedTab.add(String.join(";", filter));
        while (scanner.hasNextLine()) {
            StringJoiner joiner = new StringJoiner(";");
            String[] line = scanner.nextLine().split(";");
            for (int i = 0; i < column.length; i++) {
                joiner.add(line[column[i]]);
            }
            sortedTab.add(joiner.toString());

        }
        scanner.close();
        Path outPath = Path.of(argsName.get("out"));
        if (!argsName.get("out").equals("stdout") && !outPath.isAbsolute()) {
            throw new IllegalArgumentException("Illegal path to save");
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(argsName.get("out"), Charset.forName("UTF-8")))) {
            for (String s : sortedTab) {
                out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
