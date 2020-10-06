package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {
    protected String description;
    protected LocalDate date;
    protected LocalTime time;
    protected int repeatCount;
    protected boolean isDone;

    /**
     * Creates a new event with the given description and default its done status to false.
     *
     * @param description of event.
     */
    public Event(String description) {
        setDescription(description);
        isDone = false;
    }

    /**
     * Sets event's description.
     *
     * @param description of event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    /**
     * Sets the event's done status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of event's done status.
     *
     * @return string representation of event's done status.
     */
    public String getStatus() {
        return (isDone) ? "✓" : "✕";
    }
}
