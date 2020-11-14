package seedu.quotesify.todo;

import org.json.simple.JSONObject;
import seedu.quotesify.parser.JsonSerializer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//@@author lunzard

/**
 * Represents a task.
 */
public class ToDo implements JsonSerializer {
    private String name;
    private String deadline;
    private boolean isDone;
    private LocalDate formattedDeadline;
    private boolean isDeadlineFormatted;

    /**
     * Constructor for task with a name and a deadline.
     *
     * @param name name of the task.
     * @param deadline deadline of the task.
     */
    public ToDo(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = false;
        this.isDeadlineFormatted = false;
    }

    /**
     * Constructor for task with a name, a deadline, and a status of completion.
     *
     * @param name name of the task.
     * @param deadline deadline of the task.
     * @param isDone status of completion.
     */
    public ToDo(String name, String deadline, boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
        this.isDeadlineFormatted = false;
    }

    /**
     * Returns name of the task.
     *
     * @return name Name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the task.
     *
     * @param name Name of the task.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns deadline of the task.
     *
     * @return deadline Deadline of the task.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns status of the task.
     *
     * @return isDone status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets status of the task.
     *
     * @param done status of the task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns formatted deadline of the task.
     *
     * @return formattedDeadline Formatted deadline of the task.
     */
    public LocalDate getFormattedDeadline() {
        return formattedDeadline;
    }

    /**
     * Sets status of deadline format.
     *
     * @param isFormatted Status of deadline format.
     */
    public void setDeadlineFormatted(boolean isFormatted) {
        isDeadlineFormatted = isFormatted;
    }

    /**
     * Returns status icon of the task.
     *
     * @return status icon depending on whether the task is marked as done.
     */
    public String getStatusIcon() {
        String signTick = "v";
        String signCross = "x";
        return (isDone ? signTick : signCross);
    }

    /**
     * Convert deadline of the task to a more readable format.
     */
    public void updateDateFormat() {
        try {
            formattedDeadline = LocalDate.parse(deadline);
            isDeadlineFormatted = true;
        } catch (DateTimeParseException e) {
            formattedDeadline = LocalDate.parse("9999-12-31");
            isDeadlineFormatted = false;
        }
    }

    /**
     * Returns a string listing the task and its deadlines.
     *
     * @return String of task and its deadlines.
     */
    @Override
    public String toString() {
        if (isDeadlineFormatted) {
            return "[" + this.getStatusIcon() + "] "
                    + this.name + " (by: "
                    + formattedDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ", " + formattedDeadline.getDayOfWeek() + ")";
        } else {
            return "[" + this.getStatusIcon() + "] "
                    + this.name + " (by: " + this.deadline + ")";
        }
    }

    /**
     * Converts the task object to a JSON object.
     *
     * @return A Task object as a JSONObject.
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.getName());
        json.put("deadline", this.getDeadline());
        json.put("isDone", this.isDone());
        return json;
    }
}
