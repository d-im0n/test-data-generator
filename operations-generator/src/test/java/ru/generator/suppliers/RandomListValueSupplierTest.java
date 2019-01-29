package ru.generator.suppliers;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RandomListValueSupplierTest {
    @Test
    public void testRandomListValues() {
        List<Integer> list = Arrays.asList(111, 222, 333, 444);

        RandomListValueSupplier supplier = new RandomListValueSupplier(list);

        for (int i = 0; i < 1000; i++) {
            assertTrue(list.contains(supplier.get()));
        }
    }
}