package seedu.duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a [T] icon to indicate task as a todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
