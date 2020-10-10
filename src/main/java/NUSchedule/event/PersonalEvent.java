package NUSchedule.event;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents the Event Events.
 */
public class PersonalEvent extends Event {

    protected LocalDateTime at;

    /**
     * Convert the information about this event to a string that is to be stored in a file.
     *
     * @return the result string to be stored
     */
    public String fileString() {
        return "E//" + (isDone ? 1 : 0) + "//" + description + "//" + at;
    }

    /**
     * Provides the date of the Event.
     *
     * @return the LocalDate get from LocalDateTime
     */
    public LocalDate getDate() {
        return LocalDate.from(at);
    }

    public PersonalEvent(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format
     * Example of the format: [P][✘]a  (at: Feb 20 2020 08:00)
     */
    public String toString() {
        return "[P]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH':'mm")) + ")";
    }
}
