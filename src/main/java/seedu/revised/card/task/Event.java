package seedu.revised.card.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;


    public Event(String description, boolean isDone, LocalDateTime dateTime) {
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
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }

    public String getDateTimeDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a d MMM yyyy");
        return this.dateTime.format(formatter);
    }
}
