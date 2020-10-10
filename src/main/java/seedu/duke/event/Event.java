package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Event {
    protected String description;
    protected LocalDate date;
    protected LocalTime time;
    protected boolean isDone;
    protected Repeat repeat;

    /**
     * Creates a new event with the given description and default its done status to false.
     *
     * @param description of event.
     */
    public Event(String description) {
        setDescription(description);
        isDone = false;
        repeat = null;
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

    /**
     * Sets the event's done status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    /**
     * Returns a string representation of event's done status.
     *
     * @return string representation of event's done status.
     */
    public String getStatus() {
        return (isDone) ? "✓" : "✕";
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Gets date of the event.
     *
     * @return LocalDate object containing the date of the event
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Gets time of the event.
     *
     * @return LocalTime object containing the time of the event
     */
    public LocalTime getTime() {
        return this.time;
    }

    public ArrayList<DateStatusPair> getRepeatList() {
        return repeat.getRepeatList();
    }

    public String getRepeatType() {
        return repeat.getRepeatType();
    }

    public int getRepeatCount() {
        return repeat.getRepeatCount();
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getDescription();
    }
}
