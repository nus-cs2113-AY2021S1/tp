package seedu.duke.event;

public class Goal extends Event {
    public Goal(String description) {
        super(description);
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
