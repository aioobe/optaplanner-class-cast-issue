package vngschedules.schedule;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class Schedule {

    @PlanningEntityCollectionProperty
    private List<RecurringLecture> lectures;

    @ValueRangeProvider(id = "timeslotRange")
    @ProblemFactCollectionProperty
    private List<Timeslot> timeslots;

    @PlanningScore
    private HardSoftScore score;

    public Schedule() {
    }

    public Schedule(List<RecurringLecture> lectures, List<Timeslot> timeslots) {
        this.lectures = lectures;
        this.timeslots = timeslots;
    }
}
