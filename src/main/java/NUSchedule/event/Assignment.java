package NUSchedule.Task;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Assignment tasks.
 */
public class Assignment extends Task {

    protected LocalDateTime by;

    /**
     * Convert the information about this deadline to a string that is to be stored in a file.
     *
     * @return the result string to be stored
     */
    public String fileString() {
        return "D//" + (isDone ? 1 : 0) + "//" + description + "//" + by;
    }

    public Assignment(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Provides the date of the task.
     *
     * @return the LocalDate get from LocalDateTime
     */
    public LocalDate getDate() {
        return LocalDate.from(by);
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format
     * Example of the format: [D][✘]a  (by: Feb 20 2020 08:00)
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH':'mm")) + ")";
    }
}
