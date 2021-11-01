package vngschedules.solver;

import com.google.common.annotations.VisibleForTesting;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import vngschedules.schedule.RecurringLecture;

public class SchedulingConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory cf) {
        return new Constraint[] {
                studentsShouldHaveLunchEveryDay(cf)
        };
    }

    @VisibleForTesting
    public Constraint studentsShouldHaveLunchEveryDay(ConstraintFactory cf) {
        return cf.from(RecurringLecture.class)
                .filter(RecurringLecture::isScheduled)                     // [Lecture]...
                .groupBy(l -> l, l -> l.getStudents())    // [Lecture, Set<Student>]...
                .flattenLast(students -> students)                         // [Lecture, Student]...
                .groupBy((l, s) -> s,                                      // [Student, Map<Day, Set<Lecture>>]...
                        ConstraintCollectors.toMap(
                                (l, s) -> l.getStartTimeslot().getDay(),
                                (l, s) -> l))
                //.flattenLast(Map::values)                                // [Student, Set<Lecture>]...
                //.filter((student, dailyLectures) -> !LunchBreakAnalyzer.hasLunchBreak(dailyLectures))
                .penalize("lunch problem", HardSoftScore.ONE_HARD);

    }
}
