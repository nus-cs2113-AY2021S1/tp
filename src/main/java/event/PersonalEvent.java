package event;


import exception.EndBeforeStartEventException;
import location.Location;
import location.OnlineLocation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;


/**
 * Represents the PersonalEvent Events.
 * PersonalEvent may or may not have an end time.
 */
public class PersonalEvent extends Event {

    protected LocalDateTime at;
    protected LocalDateTime end = null;

    /**
     * Convert the information about this personal event to a string that is to be stored in a file.
     *
     * @return the result string to be stored
     */
    public String fileString() {
        return "P//" + (isDone ? 1 : 0) + "//" + description + "//" + at + "//" + (location != null
                ? location.fileString() : link.fileString());
    }

    /**
     * Provides the date of the Event.
     *
     * @return the LocalDate get from LocalDateTime
     */
    public LocalDate getDate() {
        return LocalDate.from(at);
    }

    public PersonalEvent(String description, Location location, LocalDateTime at) {
        super(description, location);
        this.at = at;
    }

    public PersonalEvent(String description, Location location, LocalDateTime at, LocalDateTime end)
            throws EndBeforeStartEventException {
        super(description, location);
        this.at = at;
        this.end = end;
        if (!end.isAfter(at)) {
            throw new EndBeforeStartEventException();
        }
    }

    public PersonalEvent(String description, OnlineLocation location, LocalDateTime at) {
        super(description, location);
        this.at = at;
    }

    public PersonalEvent(String description, OnlineLocation location, LocalDateTime at, LocalDateTime end)
            throws EndBeforeStartEventException {
        super(description, location);
        this.at = at;
        this.end = end;
        if (!end.isAfter(at)) {
            throw new EndBeforeStartEventException();
        }
    }

    /**
     * Provides the date time of the personal event.
     *
     * @return the LocalDateTime get from LocalDateTime.
     */
    public LocalDateTime getStartDateTime() {
        return LocalDateTime.from(at);
    }

    /**
     * Provides the end date time of the personal event.
     *
     * @return the LocalDateTime get from LocalDateTime.
     */
    public LocalDateTime getEndDateTime() {
        try {
            return LocalDateTime.from(end);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format.
     */
    public String toString() {
        return "[P]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")"
                + (end != null ? "\n(end at: " + at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")" :
                "")
                + "\n" + (location != null ? location : link);
    }
}
