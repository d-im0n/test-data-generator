package ru.statistic;

import ru.statistic.exception.StatisticException;
import ru.statistic.model.DaysOperationStatistic;
import ru.statistic.model.OfficesOperationStatistic;
import ru.statistic.model.Operations;
import ru.statistic.model.StatisticParameters;

public class OperationStatistic {

    public static void main(String[] args) {

        try {

            StatisticParameters parameters = new StatisticParameters(args);
            parameters.parse();

            Operations operations = new Operations(parameters.getOperationsFileName());

            operations.saveToFile(parameters.getDaysStatisticFileName(), new DaysOperationStatistic());
            operations.saveToFile(parameters.getOfficesStatisticsFileName(), new OfficesOperationStatistic());

            System.out.println(String.format("Files %s, %s succesfully created", parameters.getDaysStatisticFileName(),
                    parameters.getOfficesStatisticsFileName()));

        }catch(StatisticException e){

            System.out.println(e.getMessage());
        }
    }
}
