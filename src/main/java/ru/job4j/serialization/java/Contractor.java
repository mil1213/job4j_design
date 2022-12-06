package ru.job4j.serialization.java;

public class Contractor {
    private final String name;
    private final String idNumber;

    public Contractor(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "Contractor{"
                + "name='" + name + '\''
                + ", idNumber='" + idNumber + '\''
                + '}';
    }
}
