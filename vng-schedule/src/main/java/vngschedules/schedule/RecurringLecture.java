package vngschedules.schedule;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.List;

@PlanningEntity
public class RecurringLecture {

    private List<Student> students;

    // To make them orderable so we can filter out pairs in constraints
    // TODO: 9.13.3. Multithreaded incremental solving
    @PlanningId
    private int lectureId;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange", nullable = true)
    private Timeslot startTimeslot;

    public RecurringLecture() {
    }

    public RecurringLecture(List<Student> students, int lectureId) {
        this.students = students;
        this.lectureId = lectureId;
    }

    public RecurringLecture setStartTimeslot(Timeslot startTimeslot) {
        this.startTimeslot = startTimeslot;
        return this;
    }

    public boolean isScheduled() {
        return startTimeslot != null;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RecurringLecture && ((RecurringLecture) o).lectureId == lectureId;
    }

    @Override
    public int hashCode() {
        return lectureId;
    }

    public Timeslot getStartTimeslot() {
        return startTimeslot;
    }

    public List<Student> getStudents() {
        return students;
    }
}
