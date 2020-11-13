package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.exception.CalorieCountException;
import seedu.duke.exception.DateLimitException;
import seedu.duke.exception.DescriptionLengthExceedException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.InvalidCalorieException;
import seedu.duke.exception.InvalidNumberOfArgumentsException;
import seedu.duke.exception.ListNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

//@@author 1-Karthigeyan-1
/**
 * Prepare commands.
 * Contains methods that are shared between commands.
 */
public abstract class PrepareCommand {
    public String[] description;
    public LocalDateTime date;
    protected static final int MAXIMUM_DESCRIPTION_LENGTH = 40;
    protected static final int MAXIMUM_CALORIE_COUNT = 3000;
    protected static final int MINIMUM_CALORIE_COUNT = 0;
    protected static final int MINIMUM_INDEX = 0;
    protected static final String APPLICATION_LAUNCH_DATE = "2020-10-14";
    protected static final String SPACE = " ";
    protected static final String NOTHING = "";
    protected static final int ALPHABET_WITH_SLASH_LENGTH = 2;

    public PrepareCommand(String[] description) {
        this.description = description;
        setDate();
    }

    /**
     * Gets current date.
     */
    private void setDate() {
        this.date = LocalDateTime.now();
    }


    public abstract Command prepareCommand() throws Exception, ListNotFoundException;

    //@@author e0425705
    /**
     * Checks for index of the delete command.
     *
     * @param index index of the delete command
     * @throws IndexOutOfBoundsException if index is below 0
     */
    protected void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < MINIMUM_INDEX) {
            throw new IndexOutOfBoundsException();
        }
    }

    //@@author e0425705
    /**
     * Process date input by user.
     *
     * @param dateInput date input by user
     * @return date
     * @throws DateTimeParseException if the string is in the incorrect format
     */
    protected LocalDate processDate(String dateInput) throws DateTimeParseException {
        return LocalDate.parse(dateInput);
    }

    //@@author e0425705
    /**
     * Returns current date.
     *
     * @return current date
     */
    protected LocalDate currentDate() {
        LocalDate date = LocalDate.now();

        return date;
    }

    //@@author e0425705
    /**
     * Checks if the date is valid.
     *
     * @param dateInput is date input by user
     * @param dateIndex index date is at
     * @return true if date is valid
     * @throws DateLimitException if date exceeds limit
     */
    protected LocalDate checkDateValidity(String dateInput, int dateIndex) throws DateLimitException {
        LocalDate date = processDate(dateInput.substring(dateIndex + ALPHABET_WITH_SLASH_LENGTH).trim());
        LocalDate current = LocalDate.now();
        LocalDate past = processDate(APPLICATION_LAUNCH_DATE);
        if (date.isAfter(current)) {
            throw new DateLimitException();
        } else if (date.isBefore(past)) {
            throw new DateLimitException();
        } else {
            return date;
        }
    }

    //@@author e0425705
    /**
     * Checks if calorie input by user is empty.
     *
     * @param calorieInput calorie input by user
     * @return calorie count if it is not empty
     * @throws InvalidCalorieException if the calorie count is empty
     */
    protected int parseCalorie(String calorieInput) throws InvalidCalorieException {
        if (!calorieInput.equals(NOTHING)) {
            return Integer.parseInt(calorieInput);
        } else {
            throw new InvalidCalorieException();
        }
    }

    //@@author e0425705
    /**
     * Checks if the description is filled.
     *
     * @param description description input by user
     * @return true if no error in description input
     * @throws EmptyDescriptionException if description input has error
     */
    protected boolean isDescriptionNotEmpty(String description) throws EmptyDescriptionException {
        if (description.equals(SPACE) || description.equals(NOTHING)) {
            throw new EmptyDescriptionException();
        } else {
            return true;
        }
    }

    //@@author e0425705
    /**
     * Checks if the calorie input is within accepted range.
     *
     * @param calorie calories input by user
     * @return true is calorie is within range
     * @throws CalorieCountException if calorie not within range
     */
    protected boolean isCaloriesWithinRange(int calorie) throws CalorieCountException {
        if (calorie <= MINIMUM_CALORIE_COUNT || calorie > MAXIMUM_CALORIE_COUNT) {
            throw new CalorieCountException();
        } else {
            return true;
        }
    }

    //@@author e0425705
    /**
     * Checks if the description character counts is within accepted range.
     *
     * @param description description input by user
     * @return true if description length is within range
     * @throws DescriptionLengthExceedException if description exceeds range
     */
    protected boolean isDescriptionLengthWithinRange(String description) throws DescriptionLengthExceedException {
        if (description.length() > MAXIMUM_DESCRIPTION_LENGTH) {
            throw new DescriptionLengthExceedException();
        } else {
            return true;
        }
    }

    /**
     * Checks if the number of argument is within limit.
     *
     * @param limit limit of description length
     * @return true if description length is within limit
     * @throws InvalidNumberOfArgumentsException if description exceeds limit
     */
    protected boolean isNumberOfArgumentsValid(int limit) throws InvalidNumberOfArgumentsException {
        if (description.length != limit) {
            throw new InvalidNumberOfArgumentsException();
        }
        return true;
    }

}
