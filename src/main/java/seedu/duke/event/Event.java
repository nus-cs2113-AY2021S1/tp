package seedu.duke.event;

import seedu.duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Event implements Cloneable {
    protected String description;
    protected LocalDate date;
    protected LocalTime time;
    protected boolean isDone;
    protected ArrayList<Event> repeatEventList;
    protected String repeatType;
    protected ArrayList<String> notes;

    /**
     * Creates a new event with the given description and default its done status to false.
     *
     * @param description of event.
     */
    public Event(String description) {
        setDescription(description);
        isDone = false;
        repeatType = null;
        repeatEventList = null;
        notes = new ArrayList<String>();
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

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public void setRepeatEventList(ArrayList<Event> repeatEventList) {
        this.repeatEventList = repeatEventList;
    }

    /**
     * Sets the event's done status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the event's done status to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public void setNotes(ArrayList<String>  notes) {
        this.notes = notes;
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

    public ArrayList<Event> getRepeatEventList() {
        return repeatEventList;
    }

    public String getRepeatType() {
        if (repeatType == null) {
            return null;
        } else {
            return repeatType.toLowerCase();
        }
    }

    public int getRepeatCount() {
        if (repeatEventList == null) {
            return 0;
        }
        return repeatEventList.size();
    }

    public ArrayList<String>  getNotes() {
        return notes;
    }
    @Override
    public Event clone() throws CloneNotSupportedException {
        return (Event) super.clone();
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getDescription();
    }

    public String toCalendarString() {
        return String.format("%s | ", time.format(DateTimeFormatter.ofPattern("K:mm a")))
                + String.format("%s | ", getStatus())
                + String.format("%s ", getDescription());
    }
}
