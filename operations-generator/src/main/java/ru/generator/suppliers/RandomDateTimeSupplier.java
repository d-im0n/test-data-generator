package ru.generator.suppliers;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomDateTimeSupplier implements Supplier<LocalDateTime> {

    private final long startRangeEpochDay;
    private final long endRangeEpochDay;
    private final ZoneId zoneId;

    public RandomDateTimeSupplier() {
        this(LocalDateTime.of(LocalDate.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIDNIGHT),
                LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIDNIGHT));
    }

    public RandomDateTimeSupplier(LocalDateTime startRange, LocalDateTime endRange) {
        this(startRange, endRange, ZoneId.systemDefault());
    }

    public RandomDateTimeSupplier(LocalDateTime startRange, LocalDateTime endRange, ZoneId zoneId) {
        this.zoneId = zoneId;
        this.startRangeEpochDay = startRange.atZone(zoneId).toEpochSecond();
        this.endRangeEpochDay = endRange.atZone(zoneId).toEpochSecond();
    }

    @Override
    public LocalDateTime get(){
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(getRandomEpochMilli()), zoneId);
    }

    private long getRandomEpochMilli() {
        return ThreadLocalRandom.current().nextLong(startRangeEpochDay, endRangeEpochDay);
    }
}
