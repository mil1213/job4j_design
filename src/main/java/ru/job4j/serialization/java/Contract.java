package ru.job4j.serialization.java;

import java.util.Arrays;

public class Contract {
    private final Contractor contractor;
    private final boolean status;
    private final int sum;
    private final String subject;
    private final String[] schedule;

    public Contract(Contractor contractor, boolean status, int sum, String subject, String[] schedule) {
        this.contractor = contractor;
        this.status = status;
        this.sum = sum;
        this.subject = subject;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Contract{"
                + "contractor=" + contractor
                + ", status=" + status
                + ", sum=" + sum
                + ", subject='" + subject + '\''
                + ", schedule=" + Arrays.toString(schedule)
                + '}';
    }
}
