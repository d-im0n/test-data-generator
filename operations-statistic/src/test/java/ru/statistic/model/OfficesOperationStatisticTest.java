package ru.statistic.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OfficesOperationStatisticTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testDifferentOfficesOperations() {
        List operations = Arrays.asList(
                new Operation(LocalDateTime.now().minusMonths(1), 222, 3, 100),
                new Operation(LocalDateTime.now().minusDays(1), 111, 1, 700),
                new Operation(LocalDateTime.now(), 333, 2, 500));

        Iterator<Map.Entry<Integer, Long>> iterator =  new OfficesOperationStatistic().apply(operations).entrySet().iterator();

        Map.Entry<Integer, Long> item = iterator.next();
        assertEquals(111, item.getKey().intValue());
        assertEquals(700L, item.getValue().longValue());

        item = iterator.next();
        assertEquals(333, item.getKey().intValue());
        assertEquals(500L, item.getValue().longValue());

        item = iterator.next();
        assertEquals(222, item.getKey().intValue());
        assertEquals(100L, item.getValue().longValue());
    }

    @Test
    public void testFewOperationsPerOffice() {
        List operations = Arrays.asList(
                new Operation(LocalDateTime.now().minusMonths(1), 333, 3, 100),
                new Operation(LocalDateTime.now().minusDays(1), 111, 1, 700),
                new Operation(LocalDateTime.now(), 333, 2, 500));

        Iterator<Map.Entry<Integer, Long>> iterator =  new OfficesOperationStatistic().apply(operations).entrySet().iterator();

        Map.Entry<Integer, Long> item = iterator.next();
        assertEquals(111, item.getKey().intValue());
        assertEquals(700L, item.getValue().longValue());

        item = iterator.next();
        assertEquals(333, item.getKey().intValue());
        assertEquals(600L, item.getValue().longValue());
    }

    @Test
    public void testEmptyOperationList() {
        Map<Integer, Long> result = new OfficesOperationStatistic().apply(Collections.emptyList());

        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullOperationList() {
        exceptionRule.expect(NullPointerException.class);

        new OfficesOperationStatistic().apply(null);
    }
}