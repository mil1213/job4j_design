package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Illegal key");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Amount of arguments is not enough");
        }
        for (String arg : args) {
            String[] kv = arg.split("=", 2);
            if (kv.length == 1) {
                throw new IllegalArgumentException("Illegal pattern");
            }
            if (!kv[0].startsWith("-") || kv[0].length() == 1) {
                throw new IllegalArgumentException("Key name does not match pattern or there's no key");
            }
            if (kv[1].length() == 0) {
                throw new IllegalArgumentException("There's no value");
            }
            values.put(kv[0].substring(1), kv[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
