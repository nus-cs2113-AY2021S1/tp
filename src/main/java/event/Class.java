package event;


import location.Location;

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
     *         Example of the format: [C][✘]a.
     */
    public String toString() {
        return "[C]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")"
                + "\n" + location;
    }

    public static Comparator<Event> descriptionComparator = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o2.getDescription().substring(6).compareToIgnoreCase(o1.getDescription().substring(6));
        }
    };
}
