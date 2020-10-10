package seedu.duke.calendar.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    protected String taskType;

    private static final String TODO_FILE_SYMBOL = "T";
    private static final String SEPARATOR = "|";

    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Returns a [T] icon to indicate task as a todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the respective task type.
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String printIntoFile() {
        return TODO_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public LocalTime getTime() {
        return null;
    }
}
