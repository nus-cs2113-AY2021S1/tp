package fitr;

public class Goal {
    protected String goalType;
    protected String description;

    public Goal(String goalType, String description) {
        this.goalType = goalType;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getGoalType() {
        return goalType;
    }
}
