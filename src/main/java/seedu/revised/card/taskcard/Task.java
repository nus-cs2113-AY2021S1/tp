package seedu.revised.card.taskcard;

import java.time.LocalDateTime;

public class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks whether a task is done.
     *
     * @return true when a task is done
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Prints the String representation of the class.
     *
     * @return the String representation of the class
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.MAX;
    }
}

