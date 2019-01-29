package ru.statistic.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OfficesOperationStatistic implements Function<List<Operation>, Map<Integer, Long>> {

    @Override
    public Map<Integer, Long> apply(List<Operation> operationList) {
        return operationList.stream()
                .collect(
                        Collectors.groupingBy(o -> o.getOfficeNumber(), Collectors.summingLong(o -> o.getAmount())))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
