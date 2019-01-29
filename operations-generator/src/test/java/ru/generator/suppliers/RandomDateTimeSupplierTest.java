package ru.generator.suppliers;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.*;

public class RandomDateTimeSupplierTest {
    @Test
    public void testDefaultRandomValues() {
        RandomDateTimeSupplier supplier = new RandomDateTimeSupplier();

        LocalDateTime startRange = LocalDateTime.of(LocalDate.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIDNIGHT);
        LocalDateTime endRange = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIDNIGHT);

        for (int i = 0; i < 1000; i++) {
            LocalDateTime localDateTime = supplier.get();
            assertTrue(startRange.isBefore(localDateTime));
            assertTrue(endRange.isAfter(localDateTime));
        }
    }
}