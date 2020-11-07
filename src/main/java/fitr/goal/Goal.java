package fitr.goal;

import fitr.common.DateManager;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import java.time.LocalDate;

import static fitr.common.Messages.SYMBOL_NO;
import static fitr.common.Messages.SYMBOL_PERCENTAGE;
import static fitr.common.Messages.SYMBOL_YES;
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
        this.goalStatus = SYMBOL_NO;
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
        if (rawStatus.equals("0.0" + SYMBOL_PERCENTAGE) || rawStatus.equals(SYMBOL_NO + SYMBOL_PERCENTAGE)) {
            return SYMBOL_NO;
        } else if (rawStatus.equals("100.0" + SYMBOL_PERCENTAGE) || rawStatus.equals(SYMBOL_YES + SYMBOL_PERCENTAGE)) {
            return SYMBOL_YES;
        }
        return rawStatus;
    }

    public void markAsCompleted() {
        this.goalStatus = SYMBOL_YES;
    }

    public void setGoal(Goal goal, String goalStatus) {
        this.goalType = goal.getGoalType();
        this.goalStatus = goalStatus;
        this.description = goal.getDescription();
    }
}
