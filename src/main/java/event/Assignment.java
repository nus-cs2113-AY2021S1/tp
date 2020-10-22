package event;


import location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Represents the Assignment Events.
 */
public class Assignment extends Event {

    protected LocalDateTime by;

    /**
     * Convert the information about this assignment to a string that is to be stored in a file.
     *
     * @return the result string to be stored.
     */
    public String fileString() {
        return "A//" + (isDone ? 1 : 0) + "//" + description + "//" + by + "//" + location.fileString();
    }

    public Assignment(String description, Location location, LocalDateTime by) {
        super(description, location);
        this.by = by;
    }

    /**
     * Provides the date of the Assignment.
     *
     * @return the LocalDate get from LocalDateTime.
     */
    public LocalDate getDate() {
        return LocalDate.from(by);
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format.
     *         Example of the format: [A][✘]a  (by: 2020/02/20 08:00).
     */
    public String toString() {
        return "[A]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")"
                + "\n" + location;
    }
}
