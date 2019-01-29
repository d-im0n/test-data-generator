package ru.statistic.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DaysOperationStatisticTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testDifferentDayOperations() {
        List operations = Arrays.asList(
                new Operation(LocalDateTime.now().minusDays(1), 111, 1, 700),
                new Operation(LocalDateTime.now(), 222, 2, 500),
                new Operation(LocalDateTime.now().minusMonths(1), 333, 3, 100));

        Iterator<Map.Entry<LocalDate, Long>> iterator = new DaysOperationStatistic().apply(operations).entrySet().iterator();

        Map.Entry<LocalDate, Long> item = iterator.next();
        assertEquals(LocalDate.now().minusMonths(1), item.getKey());
        assertEquals(100L, item.getValue().longValue());

        item = iterator.next();
        assertEquals(LocalDate.now().minusDays(1), item.getKey());
        assertEquals(700L, item.getValue().longValue());

        item = iterator.next();
        assertEquals(LocalDate.now(), item.getKey());
        assertEquals(500L, item.getValue().longValue());
    }

    @Test
    public void testFewOperationsPerDay() {
        List operations = Arrays.asList(
                new Operation(LocalDateTime.now(), 111, 1, 700),
                new Operation(LocalDateTime.now(), 222, 2, 500),
                new Operation(LocalDateTime.now().minusMonths(1), 333, 3, 100));

        Iterator<Map.Entry<LocalDate, Long>> iterator = new DaysOperationStatistic().apply(operations).entrySet().iterator();

        Map.Entry<LocalDate, Long> item = iterator.next();
        assertEquals(LocalDate.now().minusMonths(1), item.getKey());
        assertEquals(100L, item.getValue().longValue());

        item = iterator.next();
        assertEquals(LocalDate.now(), item.getKey());
        assertEquals(1200L, item.getValue().longValue());
    }

    @Test
    public void testEmptyOperationList() {
        Map<LocalDate, Long> result = new DaysOperationStatistic().apply(Collections.emptyList());

        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullOperationList() {

        exceptionRule.expect(NullPointerException.class);

        new DaysOperationStatistic().apply(null);
    }
}