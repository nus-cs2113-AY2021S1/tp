package seedu.duke.model;

import java.time.LocalDate;

//@@author e0425705
/**
 * Sub-class of Activity.
 */
public class Food extends Activity {
    /**
     * Constructor of class Food.
     *
     * @param description food description
     * @param calories calories consumed
     */
    public Food(String description, int calories, LocalDate date, boolean isFromFile) {
        super(description, calories, date, isFromFile);
    }

    /**
     * Gets food string.
     *
     * @return food string
     */
    public String getString() {
        return toString();
    }

    @Override
    public String toString() {
        return "[F] | " + super.toString();
    }
}
