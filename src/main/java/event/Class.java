package event;


import location.Location;
import location.OnlineLocation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Class events.
 */
public class Class extends Event {
    protected LocalDateTime at;
    protected LocalDateTime end;

    public Class(String description, Location location, LocalDateTime at, LocalDateTime end) {
        super(description, location);
        this.at = at;
        this.end = end;
    }

    public Class(String description, OnlineLocation meeting, LocalDateTime at, LocalDateTime end) {
        super(description, meeting);
        this.at = at;
        this.end = end;
    }

    /**
     * Convert the information about this class to a string that is to be stored in a file.
     *
     * @return the result string to be stored.
     */
    public String fileString() {
        return "C//" + (isDone ? 1 : 0) + "//" + description + "//" + at + "//" + end + "//" + (location != null ?
                location.fileString() : link.fileString());
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format.
     */
    public String toString() {
        return "[C]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm"))
                + ")\n"
                + "(end at: " + end.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")"
                + "\n" + (location != null ? location : link);
    }

}
