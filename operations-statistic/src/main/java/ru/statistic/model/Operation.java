package ru.statistic.model;

import java.time.LocalDateTime;

public class Operation {

    private final int number;
    private final int officeNumber;
    private final LocalDateTime dateTime;
    private final long amount;

    public Operation(String[] params){
        this(LocalDateTime.parse(params[0].trim()),
                Integer.parseInt(params[1].trim()),
                Integer.parseInt(params[2].trim()),
                Long.parseLong(params[3].trim()));
    }

    public Operation(LocalDateTime dateTime, int officeNumber, int number, long amount) {
        this.number = number;
        this.officeNumber = officeNumber;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getAmount() {
        return amount;
    }
}
