package seedu.duke.calendar.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Exam event.
 */
public class Exam extends Task {

    private String examDetails;
    private LocalDate date;
    private LocalTime time;

    public Exam(String moduleCode, String examDetails, LocalDate date, LocalTime time) {
        super(moduleCode);
        this.examDetails = examDetails;
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a [Ex] icon to indicate task as a Exam task.
     */
    @Override
    public String toString() {
        return "[Ex]" + this.description + " (at: " + date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
                + ", " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }

}
