package ru.statistic.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DaysOperationStatistic implements Function<List<Operation>, Map<LocalDate, Long>> {

    @Override
    public Map<LocalDate, Long> apply(List<Operation> operationList) {
        return operationList.stream()
                .collect(
                        Collectors.groupingBy(o -> o.getDateTime().toLocalDate(),
                                TreeMap::new,
                                Collectors.summingLong(o -> o.getAmount())));
    }
}
