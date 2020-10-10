package seedu.duke.data;

import java.time.LocalDate;

public class Sprint {


    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;

    public Sprint(String goal) {
        this(goal, null, null);
    }

    public Sprint(String goal, LocalDate startDate) {
        this(goal, startDate, null);
    }

    public Sprint(String goal, LocalDate startDate, LocalDate endDate) {
        setGoal(goal);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
