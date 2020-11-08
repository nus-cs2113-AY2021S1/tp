package seedu.revised.card.taskcard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {


    protected LocalDateTime dateTime;

    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a d MMM yyyy");
        String dateTime = this.dateTime.format(formatter);
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

    public String getDateTimeDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a d MMM yyyy");
        return this.dateTime.format(formatter);
    }
}