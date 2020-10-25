package fitr;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import static fitr.goal.CheckGoalStatus.checkGoalStatus;

public class Goal {
    protected String createdDate;
    protected String goalType;
    protected String description;

    public Goal(String createdDate, String goalType, String description) {
        this.createdDate = createdDate;
        this.goalType = goalType;
        this.description = description;
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
        String rawStatus = checkGoalStatus(goal, foodList, exerciseList, user);
        if (rawStatus.equals("unknown!") || rawStatus.equals("0.0")) {
            return "✘";
        } else if (rawStatus.equals("100.0")) {
            return "✓";
        }
        return rawStatus + "%";
    }
}
