package ru.statistic.model;

import ru.statistic.exception.StatisticException;

public class StatisticParameters {

    private final String[] args;

    private String operationsFileName;
    private String daysStatisticFileName;
    private String officesStatisticsFileName;

    public StatisticParameters(String[] args) {
        if (args == null)
            throw new IllegalArgumentException("Args value cannot be null");

        this.args = args;
    }

    public void parse(){
        validateArgumentsQuantity();

        this.operationsFileName = args[0];
        this.daysStatisticFileName = args[1];
        this.officesStatisticsFileName = args[2];
    }

    private void validateArgumentsQuantity() {
        if (args.length < 3) {
            throw new StatisticException("Incorrect arguments quantity");
        }
    }

    public String getOperationsFileName() {
        return operationsFileName;
    }

    public String getDaysStatisticFileName() {
        return daysStatisticFileName;
    }

    public String getOfficesStatisticsFileName() {
        return officesStatisticsFileName;
    }
}
