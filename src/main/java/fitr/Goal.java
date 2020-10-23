package fitr;

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
}
