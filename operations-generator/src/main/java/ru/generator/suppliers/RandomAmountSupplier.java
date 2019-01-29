package ru.generator.suppliers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomAmountSupplier implements Supplier<Long> {
    private final long startRange;
    private final long endRange;

    public RandomAmountSupplier(long startRange, long endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    @Override
    public Long get(){
        return ThreadLocalRandom.current().nextLong(startRange, endRange + 1);
    }
}
