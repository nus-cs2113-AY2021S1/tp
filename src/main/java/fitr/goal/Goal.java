package fitr.goal;

import fitr.common.DateManager;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import java.time.LocalDate;

import static fitr.goal.CheckGoalStatus.checkGoalStatus;

public class Goal {
    protected LocalDate createdDate;
    protected String goalType;
    protected String description;
    protected String goalStatus;

    public Goal(LocalDate createdDate, String goalType, String description) {
        this.createdDate = createdDate;
        this.goalType = goalType;
        this.description = description;
        this.goalStatus = "N";
    }

    public Goal(LocalDate createdDate, String goalType, String goalStatus, String description) {
        this.createdDate = createdDate;
        this.goalType = goalType;
        this.description = description;
        this.goalStatus = goalStatus;
    }

    public String getCreatedDate() {
        return createdDate.format(DateManager.formatter);
    }

    public String getDescription() {
        return description;
    }

    public String getGoalType() {
        return goalType;
    }

    public String getStatus(Goal goal, FoodList foodList, ExerciseList exerciseList, User user) {
        String rawStatus = checkGoalStatus(goalStatus, goal, foodList, exerciseList, user) + "%";
        if (rawStatus.equals("0.0%") || rawStatus.equals("N%")) {
            return "N";
        } else if (rawStatus.equals("100.0%") || rawStatus.equals("Y%")) {
            return "Y";
        }
        return rawStatus;
    }

    public void markAsCompleted() {
        this.goalStatus = "Y";
    }

    public void setGoal(Goal goal, String goalStatus) {
        this.goalType = goal.getGoalType();
        this.goalStatus = goalStatus;
        this.description = goal.getDescription();
    }
}
