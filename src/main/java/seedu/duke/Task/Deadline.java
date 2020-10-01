package seedu.duke.Task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    /**
     * Returns a [D] icon to indicate task as a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
