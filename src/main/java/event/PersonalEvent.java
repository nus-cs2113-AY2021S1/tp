package event;


import location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;


/**
 * Represents the Event Events.
 */
public class PersonalEvent extends Event {

    protected LocalDateTime at;

    /**
     * Convert the information about this personal event to a string that is to be stored in a file.
     *
     * @return the result string to be stored
     */
    public String fileString() {
        return "P//" + (isDone ? 1 : 0) + "//" + description + "//" + at + "//" + location.fileString();
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

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format
     *         Example of the format: [P][✘]a  (at: 2020/02/20 08:00)
     */
    public String toString() {
        return "[P]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH':'mm")) + ")"
                + "\n" + location;
    }

    public static Comparator<Event> descriptionComparator = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o2.getDescription().substring(14).compareToIgnoreCase(o1.getDescription().substring(14));
        }
    };
}
