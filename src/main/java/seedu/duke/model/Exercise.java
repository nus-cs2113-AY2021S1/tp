package seedu.duke.model;

import java.time.LocalDate;

//@@author e0425705
/**
 * Sub-class of Activity.
 */
public class Exercise extends Activity {
    /**
     * Constructor of class Exercise.
     *
     * @param description exercise description
     * @param calories calories burnt
     */
    public Exercise(String description, int calories, LocalDate date, boolean isFromFile) {
        super(description, calories, date, isFromFile);
    }

    /**
     * Gets exercise string.
     *
     * @return exercise string
     */
    public String getString() {
        return toString();
    }

    @Override
    public String toString() {
        return "[E] | " + super.toString();
    }
}
