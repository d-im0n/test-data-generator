package ru.generator.model;

import java.time.LocalDateTime;

public class Operation {

    private final int number;
    private final int pointNumber;
    private final LocalDateTime dateTime;
    private final long amount;

    public Operation(int number, int pointNumber, LocalDateTime dateTime, long amount) {
        this.number = number;
        this.pointNumber = pointNumber;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return dateTime + ", " + pointNumber + ", " + number + ", " + amount;
    }
}
