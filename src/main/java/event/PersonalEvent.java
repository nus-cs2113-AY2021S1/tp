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

    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Convert the information about this personal event to a string that is to be stored in a file.
     *
     * @return the result string to be stored
     */
    public String fileString() {
        return "P//" + (isDone ? 1 : 0) + "//" + description + "//" + at + "//" + (end != null ? end + "//" : "")
                + (location != null ? location.fileString() : link.fileString());
    }

    /**
     * Provides the date of the Event.
     *
     * @return the LocalDate get from LocalDateTime
     */
    public LocalDate getDate() {
        return LocalDate.from(at);
    }

    public LocalDate getEndDate() {
        return LocalDate.from(end);
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
                + (end != null ? "\n(end at: " + end.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")" :
                "")
                + "\n" + (location != null ? location : link);
    }

    /**
     * Provides the binary operator for checking whether 2 classes are the same.
     */
    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object instanceof PersonalEvent) {
            isEqual = (this.description.equals(((PersonalEvent) object).description))
                    && (this.link != null ? (this.link.equals(((PersonalEvent) object).link))
                    : (this.location.equals(((PersonalEvent) object).location)))
                    && (this.at.isEqual(((PersonalEvent) object).at));
        }

        return isEqual;
    }
}
