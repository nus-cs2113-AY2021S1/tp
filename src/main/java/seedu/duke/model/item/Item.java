package seedu.duke.model.item;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//@@author GuoAi-reused
//Reused from https://github.com/GuoAi/ip with minor modifications
// Renamed from previous Task.java with some modifications.

/**
 * Represents an item in the list.
 */
public class Item {
    public static DateTimeFormatter DATETIME_PARSE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter DATETIME_PRINT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    protected String description;
    protected boolean isDone;
    protected int priority;
    protected String category;
    protected LocalDate date;


    /**
     * Constructor used when adding a new task.
     * By default, the deadline task is not done.
     *
     * @param description the description of the task
     */
    public Item(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Convenience constructor used when loading from the storage file.
     *
     * @param description the description of the task
     * @param isDone      true if the task is done already, false otherwise
     * @param priority    the priority of the task
     */
    public Item(String description, boolean isDone, int priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public Item(String description, boolean isReturn) {
        this.description = description;
    }

    /**
     * Retrieves the description of an item.
     *
     * @return the description string of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves whether the task in done.
     *
     * @return true if the task is done already, false otherwise
     */
    public boolean getIsDone() {
        return isDone;
    }


    /**
     * Marks the task as done and book as returned.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts the attributes of the task into a formatted string to be saved into the storage file.
     *
     * @return the formatted string to be saved into the storage file
     */
    public String toFile() {
        String isDoneString = (isDone) ? "1" : "0";
        String categoryString = (category == null) ? "" : category;
        String dateString = getDateString(Item.DATETIME_PARSE_FORMAT);
        return "T | " + isDoneString + " | " + description + " | " + priority + " | " + categoryString + " | "
                + dateString;
    }

    /**
     * Converts the attributes of the task into a formatted string to be displayed to the user.
     *
     * @return the formatted string to be displayed to the user
     */
    public String toString() {
        return this.description;
    }

    public void setDateFromString(String dateString) throws DukeException {
        assert dateString != null : "dateString should not be null.";
        try {
            date = LocalDate.parse(dateString, DATETIME_PARSE_FORMAT);
            //futureDate = date.plusMonths(1);
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.EXCEPTION_INVALID_DATE);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString(DateTimeFormatter formatter) {
        if (date == null) {
            return "";
        }

        return date.format(formatter);
    }
}
