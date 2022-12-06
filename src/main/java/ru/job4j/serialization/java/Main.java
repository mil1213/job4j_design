package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Contract contract = new Contract(new Contractor("Technogrup", "1234567"), true,
                10200000, "monolithic works", new String[] {"01.10.22", "01.12.22"});

        final Gson gson = new GsonBuilder().create();
        final String contractToJson = gson.toJson(contract);
        System.out.println(contractToJson);

        final Contract contractFromJson = gson.fromJson(contractToJson, Contract.class);
        System.out.println(contractFromJson);
    }
}
