package ru.generator.model;

import ru.generator.exception.GeneratorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Offices {

    private final String fileName;

    public Offices(String fileName) {
        this.fileName = fileName;
    }

    public List<Integer> toList() {
        try {
            return Files.lines(Paths.get(fileName)).filter(this::validate).map(Integer::valueOf).collect(Collectors.toList());
        }catch(IOException e){
            throw new GeneratorException(e);
        }
    }

    private boolean validate(String s){
        try {
            Integer.valueOf(s);
            return true;
        }catch(NumberFormatException e){
            System.err.println(String.format("Incorrect office number \"%s\" was skipped",s));
            return false;
        }
    }
}
