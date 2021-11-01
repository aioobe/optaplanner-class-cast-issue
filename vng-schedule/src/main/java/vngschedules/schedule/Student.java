package vngschedules.schedule;

import org.immutables.value.Value;

@Value.Immutable(intern = true)
public interface Student {
    @Value.Parameter
    String getName();
}
