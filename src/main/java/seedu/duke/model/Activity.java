package seedu.duke.model;

import java.time.LocalDate;

//@@author e0425705
/**
 * Stores activities attributes.
 */
public class Activity {
    protected String description;
    protected int calories;
    protected boolean isFromFile;
    protected LocalDate date;

    /**
     * Constructor of class Activity.
     *
     * @param description activity description
     * @param calories activity calories count
     */
    public Activity(String description, int calories, LocalDate date, boolean isFromFile) {
        this.description = description;
        this.calories = calories;
        this.isFromFile = isFromFile;
        this.date = date;

        // If the activity added is not from decoding the file, print confirmation message
        if (!isFromFile) {
            System.out.println(toString());
        }
    }

    /**
     * Gets activity description.
     *
     * @return activity description
     */
    public String getActivityDescription() {
        return this.description;
    }

    /**
     * Gets activity calories.
     *
     * @return activity calories
     */
    public int getActivityCalories() {
        return this.calories;
    }

    /**
     * Gets activity date.
     *
     * @return activity date
     */
    public LocalDate getActivityDate() {
        return this.date;
    }

    /**
     * Returns String to be printed out.
     *
     * @return String to be printed out.
     */
    public String toString() {
        return description + " | " + calories;
    }
}
