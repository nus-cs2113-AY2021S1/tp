package seedu.quotesify.todo;

import org.json.simple.JSONObject;
import seedu.quotesify.parser.JsonSerializer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import seedu.quotesify.ui.UiMessage;

//@@author lunzard
public class ToDo implements JsonSerializer {
    private String name;
    private String deadline;
    private boolean isDone;
    private LocalDate formattedDeadline;
    private boolean isDeadlineFormatted;

    public ToDo(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = false;
        this.isDeadlineFormatted = false;
    }

    public ToDo(String name, String deadline, boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
        this.isDeadlineFormatted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public LocalDate getFormattedDeadline() {
        return formattedDeadline;
    }

    public void setFormattedDeadline(LocalDate formattedDeadline) {
        this.formattedDeadline = formattedDeadline;
    }

    public boolean isDeadlineFormatted() {
        return isDeadlineFormatted;
    }

    public void setDeadlineFormatted(boolean isFormatted) {
        isDeadlineFormatted = isFormatted;
    }

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

    public String getStatusIcon() {
        String signTick = "v";
        String signCross = "x";
        return (isDone ? signTick : signCross);
    }

    public void updateDateFormat() {
        try {
            formattedDeadline = LocalDate.parse(deadline);
            isDeadlineFormatted = true;
        } catch (DateTimeParseException e) {
            formattedDeadline = LocalDate.parse("3000-12-31");
            isDeadlineFormatted = false;
        }
    }

    //@@author
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.getName());
        json.put("deadline", this.getDeadline());
        json.put("isDone", this.isDone());
        return json;
    }
}
