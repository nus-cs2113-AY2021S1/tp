package seedu.duke.event;

public class Goal {
    String description;

    public Goal(String description) {
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of goal.
     *
     * @return string representation of goal.
     */
    @Override
    public String toString() {
        return description;
    }
}
