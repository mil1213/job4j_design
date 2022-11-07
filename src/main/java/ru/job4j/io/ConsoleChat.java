package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> chartLog = new ArrayList<>();
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        List<String> answers = readPhrases();
        String botAnswer = "Добро пожаловать. Давайте поговорим.";
        System.out.println(botAnswer);
        chartLog.add(botAnswer);
        String userAnswer = input.nextLine();
        chartLog.add(userAnswer);
        while (!OUT.equals(userAnswer)) {
            while (!STOP.equals(userAnswer) && !OUT.equals(userAnswer)) {
                botAnswer = answers.get((r.nextInt(answers.size())));
                System.out.println(botAnswer);
                chartLog.add(botAnswer);
                userAnswer = input.nextLine();
                chartLog.add(userAnswer);
            }

            while (!CONTINUE.equals(userAnswer) && !OUT.equals(userAnswer)) {
                userAnswer = input.nextLine();
                chartLog.add(userAnswer);
            }
        }

        botAnswer = "Всего доброго!";
        System.out.println(botAnswer);
        chartLog.add(botAnswer);
        saveLog(chartLog);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.botAnswers, Charset.forName("UTF-8")))) {
            answers = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(this.path, Charset.forName("UTF-8"))))) {
            for (String s : log) {
                out.printf("%s%n", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("chartLog.txt", "botAnswers.txt");
        cc.run();
    }
}
