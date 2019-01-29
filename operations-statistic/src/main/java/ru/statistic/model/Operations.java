package ru.statistic.model;

import ru.statistic.exception.StatisticException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class Operations {
    private final String fileName;

    private List<Operation> list;

    public Operations(String fileName) {
        this.fileName = fileName;
    }

    private void load(){

        try {
            list = Files.lines(Paths.get(fileName))
                    .map(s -> s.split(","))
                    .filter(this::validateRow)
                    .map(s -> new Operation(s))
                    .collect(Collectors.toList());

        }catch(IOException e){
            throw new StatisticException(e);
        }
    }

    private boolean validateRow(String[] rowArgs){
        return rowArgs.length == 4
                && check(rowArgs[0], LocalDateTime::parse)
                && check(rowArgs[1], Integer::parseInt)
                && check(rowArgs[2], Integer::parseInt)
                && check(rowArgs[3], Long::parseLong);
    }

    private <R> boolean check(String s, Function<String, R> f){
        try{
            f.apply(s.trim());
            return true;
        }catch(RuntimeException e){
            System.err.println(String.format("Incorrect value \"%s\" was skipped",s));
            return false;
        }
    }

    public <K, V> void saveToFile(String name, Function<List<Operation>, Map<K, V>> statistic){
        load();
        streamToFile(name, statistic.apply(this.list));
    }

    private <K, V> void streamToFile(String name, Map<K, V> statistic){
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(name)))) {

            statistic.entrySet().forEach(e -> writer.println(e.getKey() + " : " + e.getValue()));

        }catch(IOException e){
            throw new StatisticException(e);
        }
    }
}
