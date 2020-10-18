package seedu.duke.calendar.task;

import seedu.duke.calendar.CalendarItem;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Task in the task list.
 */
public abstract class Task extends CalendarItem {
    public static final String TICK_SYMBOL = "/";
    public static final String CROSS_SYMBOL = "X";
    protected String description;
    protected boolean isDone;
    protected boolean isImportant;

    private static final String TASK_FILE_SYMBOL = "Task";
    private static final String SEPARATOR = "|";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isImportant = false;
    }

    /**
     * Returns the status of the task and the description of the task.
     */
    @Override
    public String toString() {
        String priorityMessage = "";
        if (isImportant) {
            priorityMessage = "(important!) ";
        }
        return "[" + this.getStatusIcon() + "] " + priorityMessage + this.description;
    }

    /**
     * Returns the description of the task.
     */
    @Override
    public String getDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a tick if the task is done. Returns a cross if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL); //return tick or X symbols
    }

    /**
     * Returns empty string, will be overridden in different task types by
     * the corresponding task type.
     *
     * @return default task type is empty.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Prioritize the task as important.
     */
    public void markAsImportant() {
        this.isImportant = true;
    }

    /**
     * Get whether the task is marked as important.
     *
     * @return whether the task is important
     */
    public boolean getIsImportant() {
        return isImportant;
    }

    /**
     * Saves the task into files.
     *
     * @return string contains the information about the activity event.
     */
    @Override
    public String printIntoFile() {
        return TASK_FILE_SYMBOL + SEPARATOR + isDone + SEPARATOR + description;
    }

    /**
     * Gets the state of the task.
     *
     * @return the state of the task
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the date of the task.
     */
    @Override
    public LocalDate getDate() {
        return null;
    }

    /**
     * Returns the time of the task.
     */
    @Override
    public LocalTime getTime() {
        return null;
    }

}
