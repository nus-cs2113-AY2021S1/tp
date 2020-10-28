package event;


import location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Represents the Class events.
 */
public class Class extends Event {
    protected LocalDateTime at;

    public Class(String description, Location location, LocalDateTime at) {
        super(description, location);

        this.at = at;
    }

    /**
     * Provides the date of the Class.
     *
     * @return the LocalDate get from LocalDateTime.
     */
    @Override
    public LocalDate getDate() {
        return LocalDate.from(at);
    }

    /**
     * Convert the information about this class to a string that is to be stored in a file.
     *
     * @return the result string to be stored.
     */
    public String fileString() {
        return "C//" + (isDone ? 1 : 0) + "//" + description + "//" + at + "//" + location.fileString();
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format.
     *         Example of the format: [C][Not Done]a (at: 2020/02/20).
     */
    public String toString() {
        return "[C]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")"
                + "\n" + location;
    }

}
