package seedu.duke.data.timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents an Event. Contains all the information of an Event.
 */
public class Event {
    private String title;
    // Can be combined into LocalDateTime
    private LocalDate date;
    private LocalTime time;
    private Boolean isToRemind;
    private Boolean isRecurring;

    /**
     * Creates an Event object with its title, date and time provided.
     *
     * @param title of the event.
     * @param date of the event.
     * @param time of the event.
     */
    public Event(String title, LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.isToRemind = false;
        this.isRecurring = false;
    }

    /**
     * Creates an Event object with its title, date, time, isToRemind and isRecurring provided.
     *
     * @param title of the event.
     * @param date of the event.
     * @param time of the event.
     * @param isToRemind status of the event.
     * @param isRecurring status of the event.
     */
    public Event(String title, LocalDate date, LocalTime time, boolean isToRemind, boolean isRecurring) {
        this(title, date, time);
        this.isToRemind = isToRemind;
        this.isRecurring = isRecurring;
    }

    public Event(String title, LocalDateTime dateTime, boolean isToRemind, boolean isRecurring) {
        this(title, dateTime.toLocalDate(), dateTime.toLocalTime(), isToRemind, isRecurring);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean getToRemind() {
        return isToRemind;
    }

    public void setToRemind(boolean toRemind) {
        isToRemind = toRemind;
    }

    public boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public String toString() {
        String titleString = "Event: " + title;
        String dateString = "Date: " + date.toString() + "\tTime: " + time.toString();
        String remindString = "Reminder: " + isToRemind;
        String repeatingString = "Repeating: " + isRecurring;
        return titleString + "\n" + dateString + "\n" + remindString + "\n" + repeatingString;
    }
}
