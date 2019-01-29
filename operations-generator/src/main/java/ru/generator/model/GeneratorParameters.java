package ru.generator.model;

import ru.generator.exception.GeneratorException;

public class GeneratorParameters {

    private final String[] args;

    private int operationCount;
    private String officesFileName;
    private String targetFileName;

    public GeneratorParameters(String[] args) {
        if (args == null || args.length < 3)
            throw new IllegalArgumentException("Number of arguments is incorrect");

        this.args = args;
    }

    public void parse(){

        validateAndSetOperationCount();

        this.officesFileName = args[0];
        this.targetFileName = args[2];
    }

    private void validateAndSetOperationCount() {
        try {
            operationCount = Integer.valueOf(args[1]);
        }catch(NumberFormatException e){
            throw new GeneratorException("Operation count value is incorrect");
        }
    }

    public int getOperationCount() {
        return operationCount;
    }

    public String getOfficesFileName() {
        return officesFileName;
    }

    public String getTargetFileName() {
        return targetFileName;
    }
}
