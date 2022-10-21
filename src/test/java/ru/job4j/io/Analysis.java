package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    public void unavailable(String source, String target) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            boolean teg = true;
            //List<String> rsl = new ArrayList<>();
            String str1 = "";
            String str2 = "";
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] temp = line.split(" ");
                if (teg && ("400".equals(temp[0]) || "500".equals(temp[0]))) {
                    str1 = temp[1] + ";";
                    teg = false;
                } else {
                    if (!teg && ("200".equals(temp[0]) || "300".equals(temp[0]))) {
                        str2 = temp[1] + ";";
                        teg = true;
                        rsl.add(str1 + str2);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String s : rsl) {
                out.printf("%s%n", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analysis analysis = new Analysis();
        analysis.unavailable("source.txt", "unavailable2.csv");
    }
}
