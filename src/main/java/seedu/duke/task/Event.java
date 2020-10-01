package seedu.duke.task;


/**
 * Represents an event task.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    /**
     * Returns a [E] icon to indicate task as a event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
