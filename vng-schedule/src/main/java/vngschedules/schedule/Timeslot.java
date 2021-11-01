package vngschedules.schedule;

import org.immutables.value.Value;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Value.Immutable(intern = true)
public abstract class Timeslot {

    final static AtomicInteger counter = new AtomicInteger(0);

    @PlanningId
    final int timeslotId = counter.getAndIncrement();

    @Value.Parameter
    public abstract DayOfWeek getDay();

    @Value.Parameter
    public abstract LocalTime startTime();
}
