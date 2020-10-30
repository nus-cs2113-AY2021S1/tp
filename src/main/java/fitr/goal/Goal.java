package fitr.goal;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import static fitr.goal.CheckGoalStatus.checkGoalStatus;

public class Goal {
    protected String createdDate;
    protected String goalType;
    protected String description;
    protected String goalStatus;

    public Goal(String createdDate, String goalType, String description) {
        this.createdDate = createdDate;
        this.goalType = goalType;
        this.description = description;
        this.goalStatus = "0.0";
    }

    public Goal(String createdDate, String goalType, String goalStatus, String description) {
        this.createdDate = createdDate;
        this.goalType = goalType;
        this.description = description;
        this.goalStatus = goalStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public String getGoalType() {
        return goalType;
    }

    public String getStatus(Goal goal, FoodList foodList, ExerciseList exerciseList, User user) {
        String rawStatus = checkGoalStatus(goalStatus, goal, foodList, exerciseList, user) + "%";
        if (rawStatus.equals("0.0%") || rawStatus.equals("✘%")) {
            return "✘";
        } else if (rawStatus.equals("100.0%") || rawStatus.equals("✓%")) {
            return "✓";
        }
        return rawStatus;
    }

    public void markAsCompleted() {
        this.goalStatus = "✓";
    }

    public void setGoal(Goal goal, String goalStatus) {
        this.createdDate = goal.getCreatedDate();
        this.goalType = goal.getGoalType();
        this.goalStatus = goalStatus;
        this.description = goal.getDescription();
    }
}
