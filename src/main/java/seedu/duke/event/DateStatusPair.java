package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateStatusPair {
    private LocalDate date;
    private LocalTime time;
    private boolean isDone;

    /**
     * Default Constructor class.
     */
    public DateStatusPair() {
        date = null;
        time = null;
        isDone = false;
    }

    /**
     * Constructor class if date and time information are provided.
     *
     * @param d LocalDate object containing the date of the structure
     * @param t LocalTime object containing the time of the structure
     */
    public DateStatusPair(LocalDate d, LocalTime t) {
        date = d;
        time = t;
        isDone = false;
    }

    /**
     * Getter for date in Date Status Pair.
     *
     * @return LocalDate object containing date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter for time in Time Status Pair.
     *
     * @return LocalTime object containing time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Getter for status in Date status pair.
     *
     * @return boolean containing the status of the event
     */
    public String getStatus() {
        return (isDone) ? "✓" : "✕";
    }

    /**
     * Setter for the date of the structure.
     *
     * @param dateToSet LocalDate object to set the date to change to for the structure
     */
    public void setDate(LocalDate dateToSet) {
        date = dateToSet;
    }

    /**
     * Setter for the time of the structure.
     *
     * @param timeToSet LocalTime object to set the time to change to for the structure
     */
    public void setTime(LocalTime timeToSet) {
        time = timeToSet;
    }

    /**
     * Setter for the status of the structure.
     *
     * @param newStatus boolean to change for the structure
     */
    public void setDone(boolean newStatus) {
        isDone = newStatus;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("K:mm a")) + " [" + getStatus() + "]";
    }
}
