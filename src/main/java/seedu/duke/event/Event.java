package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Event {
    protected String description;
    protected LocalDate date;
    protected LocalTime time;
    protected int repeatCount;
    protected boolean isDone;
    protected int repeatUnit; //weekly or monthly
    protected ArrayList<DateStatusPair> dateTimeList;

    protected static final int WEEKLY = 1;
    protected static final int MONTHLY = 2;


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

    public String getDescription() {
        return this.description;
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

    public void setRepeatUnit(int repeatUnit) {
        this.repeatUnit = repeatUnit;
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


    /**
     * Returns a String representation of an event's repeat status.
     *
     * @return string representation of event's repeat status, with starting date, repetition amount and time
     */
    public String getRepeatStatus() {
        String dateString;
        String repeatNumber;
        String repeatTimeInterval;

        dateString = this.date.toString();
        repeatNumber = Integer.toString(repeatCount);
        switch (repeatUnit) {
        case WEEKLY:
            repeatTimeInterval = "Weekly";
            break;
        case MONTHLY:
            repeatTimeInterval = "Monthly";
            break;
        default:
            repeatTimeInterval = "NULL";

        }

        return "Repeat Status: " + repeatTimeInterval + " for " + repeatNumber + " time(s) starting from " + dateString;

    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getDescription();

    }
}
