package event;

import exception.EndBeforeStartEventException;
import location.Location;
import location.OnlineLocation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelfStudy extends PersonalEvent {
    public SelfStudy(String description, Location location, LocalDateTime at) {
        super(description, location, at);
    }

    public SelfStudy(String description, Location location, LocalDateTime at, LocalDateTime end)
            throws EndBeforeStartEventException {
        super(description, location, at, end);
    }

    public SelfStudy(String description, OnlineLocation location, LocalDateTime at) {
        super(description, location, at);
    }

    public SelfStudy(String description, OnlineLocation location, LocalDateTime at, LocalDateTime end)
            throws EndBeforeStartEventException {
        super(description, location, at, end);
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format.
     */
    public String toString() {
        return "[S]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")"
                + (end != null ? "\n(end at: " + end.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")" :
                "")
                + "\n" + (location != null ? location : link);
    }

    /**
     * Convert the information about this self study event to a string that is to be stored in a file.
     *
     * @return the result string to be stored
     */
    public String fileString() {
        return "S//" + (isDone ? 1 : 0) + "//" + description + "//" + at + "//" + (end != null ? end + "//" : "")
                + (location != null ? location.fileString() : link.fileString());
    }
}
