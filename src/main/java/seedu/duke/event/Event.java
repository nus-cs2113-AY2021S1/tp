package seedu.duke.event;

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
        assert notes.size() == 0 : "Notes should be empty";

    }

    /**
     * Sets event's description.
     *
     * @param description of event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets event's date.
     *
     * @param date of event.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets event's time.
     *
     * @param time of event.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Sets event's repeatType.
     *
     * @param repeatType of event.
     */
    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    /**
     * Sets event's repeatEventList.
     *
     * @param repeatEventList to set to.
     */
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

    /**
     * Set notes for event.
     *
     * @param notes for event.
     */
    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    /**
     * Returns a string representation of event's done status.
     *
     * @return string representation of event's done status.
     */
    public String getStatus() {
        return (isDone) ? "O" : "X";
    }

    /**
     * Returns the event's description.
     *
     * @return event's description.
     */
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

    /**
     * Returns an ArrayList of Event containing the Events that are repeated from this Event.
     *
     * @return RepeatEventList as an ArrayList of Event.
     */
    public ArrayList<Event> getRepeatEventList() {
        return repeatEventList;
    }

    /**
     * Returns the repeatType of the event.
     *
     * @return repeatType of event.
     */
    public String getRepeatType() {
        if (repeatType == null) {
            return null;
        } else {
            return repeatType.toLowerCase();
        }
    }

    /**
     * Returns the repeatCount of the event.
     *
     * @return repeatCount of event.
     */
    public int getRepeatCount() {
        if (repeatEventList == null) {
            return 0;
        }
        return repeatEventList.size();
    }

    /**
     * Get notes for event.
     *
     * @return list of notes.
     */
    public ArrayList<String> getNotes() {
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

    /**
     * Returns a String representation of the event details formatted for calendar output.
     *
     * @return String representation of event in calendar format.
     */
    public String toCalendarString() {
        return String.format("%s | ", time.format(DateTimeFormatter.ofPattern("h:mm a")))
                + String.format("%s | ", getStatus())
                + String.format("%s ", getDescription());
    }
}
