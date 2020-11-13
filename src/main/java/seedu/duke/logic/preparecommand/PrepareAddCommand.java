package seedu.duke.logic.preparecommand;

import seedu.duke.command.AddExerciseCommand;
import seedu.duke.command.AddFoodCommand;
import seedu.duke.command.Command;
import seedu.duke.exception.CalorieCountException;
import seedu.duke.exception.CalorieTagNotFoundException;
import seedu.duke.exception.DateLimitException;
import seedu.duke.exception.DescriptionLengthExceedException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.InvalidCalorieException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.duke.ui.ExceptionMessages.displayAddActivityExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayAddCommandErrorMessage;
import static seedu.duke.ui.ExceptionMessages.displayCalorieCountOutOfBoundMessage;
import static seedu.duke.ui.ExceptionMessages.displayCalorieTagNotFoundExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayDateLimitExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayDateTimeExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayDescriptionLengthExceedExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayEmptyDescriptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayIncorrectDateTimeFormatEnteredMessage;
import static seedu.duke.ui.ExceptionMessages.displayInvalidCalorieExceptionMessage;
import static seedu.duke.ui.Ui.displayAddMessage;

//@@author e0425705
/**
 * Prepares user input for add command.
 */
public class PrepareAddCommand extends PrepareCommand {
    protected static final String CALORIE_TAG = "c/";
    protected static final String FOOD_TAG = "f/";
    protected static final String DATE_TAG = "d/";
    protected static final int ALPHABET_WITH_SLASH_LENGTH = 2;
    protected static final int INDEX_NOT_FOUND = -1;
    protected static final String EXERCISE_TAG = "e/";
    protected static final boolean FALSE = false;

    public PrepareAddCommand(String[] description) {
        super(description);
    }

    /**
     * Prepares the add command by checking the userInput.
     *
     * @return AddFoodCommand or AddExerciseCommand
     */
    @Override
    public Command prepareCommand() {
        try {
            if (description[1].startsWith(FOOD_TAG)) {
                int calorieIndex = description[1].indexOf(CALORIE_TAG);
                String foodDescription = getAddActivityDescription(calorieIndex);
                boolean isDescriptionInputValid = isDescriptionNotEmpty(foodDescription)
                        && isDescriptionLengthWithinRange(foodDescription);

                int dateIndex = description[1].indexOf(DATE_TAG);
                String calorieInput = getAddActivityCalorieCount(calorieIndex, dateIndex);
                int calories = parseCalorie(calorieInput);
                boolean isCalorieValid = isCaloriesWithinRange(calories);

                LocalDate date = getAddActivityDate(isDescriptionInputValid, dateIndex, isCalorieValid);

                checkIfInputValuesForAddValid(isDescriptionInputValid, isCalorieValid);

                assert calories > 0 : "calories should be greater than 0";
                return new AddFoodCommand(foodDescription, calories, FALSE, date);
            } else if (description[1].startsWith(EXERCISE_TAG)) {
                int calorieIndex = description[1].indexOf(CALORIE_TAG);
                String exerciseDescription = getAddActivityDescription(calorieIndex);
                boolean isDescriptionInputValid = isDescriptionNotEmpty(exerciseDescription)
                        && isDescriptionLengthWithinRange(exerciseDescription);

                int dateIndex = description[1].indexOf(DATE_TAG);
                String calorieInput = getAddActivityCalorieCount(calorieIndex, dateIndex);
                int calories = parseCalorie(calorieInput);
                boolean isCalorieValid = isCaloriesWithinRange(calories);

                LocalDate date = getAddActivityDate(isDescriptionInputValid, dateIndex, isCalorieValid);

                checkIfInputValuesForAddValid(isDescriptionInputValid, isCalorieValid);

                assert calories > 0 : "calories should be greater than 0";
                return new AddExerciseCommand(exerciseDescription, calories, FALSE, date);
            } else {
                displayAddActivityExceptionMessage();
            }
        } catch (DateTimeParseException e) {
            displayIncorrectDateTimeFormatEnteredMessage();
        } catch (CalorieCountException e) {
            displayCalorieCountOutOfBoundMessage();
        } catch (CalorieTagNotFoundException e) {
            displayCalorieTagNotFoundExceptionMessage();
        } catch (DescriptionLengthExceedException e) {
            displayDescriptionLengthExceedExceptionMessage();
        } catch (InvalidCalorieException e) {
            displayInvalidCalorieExceptionMessage();
        } catch (DateLimitException e) {
            displayDateLimitExceptionMessage();
        } catch (DateTimeException e) {
            displayDateTimeExceptionMessage();
        } catch (EmptyDescriptionException e) {
            displayEmptyDescriptionMessage();
        } catch (NumberFormatException e) {
            displayAddActivityExceptionMessage();
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            displayAddCommandErrorMessage();
        } catch (Exception e) {
            displayAddActivityExceptionMessage();
        }
        return null;
    }

    /**
     * Returns activity date.
     *
     * @param isDescriptionInputValid boolean variable annotating if the description is valid
     * @param dateIndex index of date tag in user input
     * @param isCalorieValid boolean variable annotating if the calorie count is valid
     * @return activity date
     * @throws DateLimitException if date entered is not valid
     */
    protected LocalDate getAddActivityDate(boolean isDescriptionInputValid, int dateIndex, boolean isCalorieValid)
            throws DateLimitException {
        LocalDate date;
        if (isCalorieValid && isDescriptionInputValid && dateIndex == INDEX_NOT_FOUND) {
            date = currentDate();
        } else {
            date = checkDateValidity(description[1], dateIndex);
        }
        return date;
    }

    /**
     * Return calorie count input by user.
     *
     * @param calorieIndex index of calorie tag in user input
     * @param dateIndex index of date tag in user input
     * @return calorie count input by user
     */
    protected String getAddActivityCalorieCount(int calorieIndex, int dateIndex) {
        String calorieInput;
        if (dateIndex == INDEX_NOT_FOUND) {
            calorieInput = description[1].substring(calorieIndex + ALPHABET_WITH_SLASH_LENGTH).trim();
        } else {
            calorieInput = description[1].substring(calorieIndex + ALPHABET_WITH_SLASH_LENGTH, dateIndex).trim();
        }
        return calorieInput;
    }

    /**
     * Checks if the input parameters of the user is valid.
     *
     * @param isDescriptionInputValid boolean variable annotating if the description is valid
     * @param isCalorieValid boolean variable annotating if the calorie count is valid
     * @throws Exception if either one of the boolean variable is false
     */
    protected void checkIfInputValuesForAddValid(boolean isDescriptionInputValid, boolean isCalorieValid)
            throws Exception {
        if (isDescriptionInputValid && isCalorieValid) {
            displayAddMessage();
        } else {
            throw new Exception();
        }
    }

    /**
     * Returns activity description.
     *
     * @param calorieIndex index of calorie tag in user input
     * @return activity description
     * @throws CalorieTagNotFoundException if the calorie tag is not found in user input
     */
    protected String getAddActivityDescription(int calorieIndex) throws CalorieTagNotFoundException {
        String foodDescription;
        if (calorieIndex == INDEX_NOT_FOUND) {
            throw new CalorieTagNotFoundException();
        } else {
            foodDescription = description[1].substring(ALPHABET_WITH_SLASH_LENGTH, calorieIndex).trim();
        }
        return foodDescription;
    }

}
