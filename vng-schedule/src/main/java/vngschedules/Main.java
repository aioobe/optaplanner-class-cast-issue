package vngschedules;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import vngschedules.schedule.ImmutableStudent;
import vngschedules.schedule.ImmutableTimeslot;
import vngschedules.schedule.RecurringLecture;
import vngschedules.schedule.Schedule;
import vngschedules.schedule.Student;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Student> students = List.of(
                ImmutableStudent.of("student 1"),
                ImmutableStudent.of("student 2"));

        Schedule schedule = new Schedule(
                List.of(new RecurringLecture(students, 0),
                        new RecurringLecture(students, 1)),
                List.of(ImmutableTimeslot.of(DayOfWeek.MONDAY, LocalTime.of(8, 0)),
                        ImmutableTimeslot.of(DayOfWeek.MONDAY, LocalTime.of(9, 0))));

        SolverFactory<Object> solverFactory = SolverFactory.createFromXmlResource("scheduling-config.xml");
        Solver<Object> solver = solverFactory.buildSolver();
        solver.solve(schedule);
    }
}
