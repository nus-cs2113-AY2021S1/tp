package seedu.duke.event;

public class Goal extends Event {
    public Goal(String description) {
        super(description);
    }

    /**
     * Changes the description of goal to the given description.
     *
     * @param description to change to.
     */
    public void changeGoal(String description) {
        setDescription(description);
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
