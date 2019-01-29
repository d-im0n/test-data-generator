package ru.generator.model;

import ru.generator.exception.GeneratorException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class RandomOperations {
    private final int count;
    private final Supplier<Integer> pointNumberSupplier;
    private final Supplier<LocalDateTime> dateTimeSupplier;
    private final Supplier<Long> amountSupplier;

    public RandomOperations(int count,
                            Supplier<Integer> pointNumberSupplier,
                            Supplier<LocalDateTime> dateTimeSupplier,
                            Supplier<Long> amountSupplier) {
        this.count = count;
        this.pointNumberSupplier = pointNumberSupplier;
        this.dateTimeSupplier = dateTimeSupplier;
        this.amountSupplier = amountSupplier;
    }

    public void generateToFile(String name) {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(name)))) {

            streamToFile(writer);

        }catch(IOException e){
            throw new GeneratorException(e);
        }
    }

    private void streamToFile(PrintWriter writer) {
        IntStream.range(1, count + 1)
                .mapToObj(i -> new Operation(i, pointNumberSupplier.get(), dateTimeSupplier.get(), amountSupplier.get()))
                .map(Operation::toString)
                .forEach(writer::println);
    }
}
