package ru.generator;

import ru.generator.exception.GeneratorException;
import ru.generator.model.GeneratorParameters;
import ru.generator.model.Offices;
import ru.generator.model.RandomOperations;
import ru.generator.suppliers.RandomAmountSupplier;
import ru.generator.suppliers.RandomDateTimeSupplier;
import ru.generator.suppliers.RandomListValueSupplier;

import java.util.List;

public class OperationGenerator {

    public static void main(String[] args) {

        try {

            GeneratorParameters parameters = new GeneratorParameters(args);

            parameters.parse();

            List<Integer> offices = new Offices(parameters.getOfficesFileName()).toList();

            new RandomOperations(parameters.getOperationCount(),
                    new RandomListValueSupplier(offices),
                    new RandomDateTimeSupplier(),
                    new RandomAmountSupplier(10_000, 100_000))
                    .generateToFile(parameters.getTargetFileName());

            System.out.println(String.format("File %s succesfully created", parameters.getTargetFileName()));

        }catch(GeneratorException e){
            System.out.println(e.getMessage());
        }
    }
}
