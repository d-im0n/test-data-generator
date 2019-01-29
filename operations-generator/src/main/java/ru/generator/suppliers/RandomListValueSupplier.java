package ru.generator.suppliers;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomListValueSupplier implements Supplier<Integer> {

    private final List<Integer> list;

    public RandomListValueSupplier(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer get(){
        return list.get(getRandomListIndex());
    }

    private int getRandomListIndex() {
        return ThreadLocalRandom.current().nextInt(list.size());
    }
}
