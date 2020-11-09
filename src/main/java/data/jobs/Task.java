//@@author TomLBZ

package data.jobs;

import constants.Constants;
import data.Item;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * The type Task.
 */
public class Task extends Item {

    /**
     * The Is done.
     */
    protected boolean isDone;
    protected String timeString;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        super(description);
        this.isDone = false;
        this.description = description;
        dateTime = null;
        isDated = false;
        isWeekly = false;
    }

    public void setTimeString(String input) {
        timeString = input;
    }

    /**
     * Gets description of the task.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get status icon string.
     *
     * @return the string
     */
    public String getStatusIcon() {
        return (isDone ? Constants.TICK : Constants.CROSS);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDateTimeString() {
        if (isDated) {
            String result;
            DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
            if (dateTime == null) {
                result = null;
            } else {
                String date = getDate().format(datePattern);
                String time = getTime().toString();
                result = date + Constants.SPACE + time;
            }
            return result;
        } else {
            return null;
        }
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }
}
