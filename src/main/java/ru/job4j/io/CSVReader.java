package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String[] filter = argsName.get("filter").split(",");
        Scanner scanner = new Scanner(new File(argsName.get("path")));
        String[] firstLine = scanner.nextLine().split(argsName.get("delimiter"));
        int[] column = new int[filter.length];

        boolean filterExist = false;

        for (int j = 0; j < column.length; j++) {
            for (int i = 0; i < firstLine.length; i++) {
                if (filter[j].equals(firstLine[i])) {
                    column[j] = i;
                    filterExist = true;
                }
            }
        }

        if (!filterExist) {
            throw new IllegalArgumentException("Selected data is missing");
        }

        List<String> sortedTab = new ArrayList<>();
        sortedTab.add(String.join(argsName.get("delimiter"), filter));
        while (scanner.hasNextLine()) {
            StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
            String[] line = scanner.nextLine().split(argsName.get("delimiter"));
            for (int i = 0; i < column.length; i++) {
                joiner.add(line[column[i]]);
            }
            sortedTab.add(joiner.toString());

        }
        scanner.close();
        try (PrintWriter out = new PrintWriter(new FileWriter(argsName.get("out"), Charset.forName("UTF-8")))) {
            for (String s : sortedTab) {
                out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate(ArgsName n) {
        if (!n.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Illegal file's name");
        }
        if (!n.get("delimiter").equals(";")) {
            throw new IllegalArgumentException("Illegal delimiter");
        }
        if (!n.get("out").equals("stdout") && !Path.of(n.get("out")).isAbsolute()) {
            throw new IllegalArgumentException("Illegal path to save");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
        try (Scanner source = new Scanner(new File(argsName.get("path")))) {
            System.out.println("Source ");
            while (source.hasNextLine()) {
                System.out.println(source.nextLine());
            }
        }
        try (Scanner rsl = new Scanner(new File(argsName.get("out")))) {
            System.out.println("Result ");
            while (rsl.hasNextLine()) {
                System.out.println(rsl.nextLine());
            }
        }
    }
}
