package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.command.EditExerciseCommand;
import seedu.duke.command.EditFoodCommand;
import seedu.duke.exception.CalorieCountException;
import seedu.duke.exception.CalorieTagNotFoundException;
import seedu.duke.exception.DescriptionLengthExceedException;
import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.InvalidCalorieException;

import static seedu.duke.ui.ExceptionMessages.displayCalorieCountOutOfBoundMessage;
import static seedu.duke.ui.ExceptionMessages.displayCalorieTagNotFoundExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayDescriptionLengthExceedExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayEditActivityExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayEmptyDescriptionMessage;
import static seedu.duke.ui.ExceptionMessages.displayInvalidCalorieExceptionMessage;

//@@author e0425705
/**
 * Prepares user input for edit command.
 */
public class PrepareEditCommand extends PrepareCommand {
    protected static final String CALORIE_TAG = "c/";
    protected static final String FOOD_TAG = "f/";
    protected static final int ALPHABET_WITH_SLASH_LENGTH = 2;
    protected static final int INDEX_NOT_FOUND = -1;
    protected static final String EXERCISE_TAG = "e/";
    protected static final String SPACE = " ";

    public PrepareEditCommand(String[] description) {
        super(description);
    }

    /**
     * Prepares the edit command by checking the userInput.
     *
     * @return EditFoodCommand or EditExerciseCommand
     */
    @Override
    public Command prepareCommand() {
        try {
            String[] arguments = description[1].split(SPACE, 2);
            int index = Integer.parseInt(arguments[0]) - 1;
            String userInput = arguments[1];

            if (userInput.startsWith(FOOD_TAG)) {
                int calorieIndex = userInput.indexOf(CALORIE_TAG);
                String foodDescription = getEditActivityDescription(userInput, calorieIndex);
                boolean isDescriptionInputValid = isDescriptionNotEmpty(foodDescription)
                        && isDescriptionLengthWithinRange(foodDescription);

                String calorieInput = userInput.substring(calorieIndex + ALPHABET_WITH_SLASH_LENGTH).trim();
                int calories = parseCalorie(calorieInput);
                boolean isCalorieValid = isCaloriesWithinRange(calories);

                checkIfInputValuesForEditValid(isDescriptionInputValid, isCalorieValid);

                assert calories > 0 : "calories should be > 0";
                return new EditFoodCommand(index, foodDescription, calories);
            } else if (userInput.startsWith(EXERCISE_TAG)) {
                int calorieIndex = userInput.indexOf(CALORIE_TAG);
                String exerciseDescription = getEditActivityDescription(userInput, calorieIndex);
                boolean isDescriptionInputValid = isDescriptionNotEmpty(exerciseDescription)
                        && isDescriptionLengthWithinRange(exerciseDescription);

                String calorieInput = userInput.substring(calorieIndex + ALPHABET_WITH_SLASH_LENGTH).trim();
                int calories = parseCalorie(calorieInput);
                boolean isCalorieValid = isCaloriesWithinRange(calories);

                checkIfInputValuesForEditValid(isDescriptionInputValid, isCalorieValid);

                assert calories > 0 : "calories should be > 0";
                return new EditExerciseCommand(index, exerciseDescription, calories);
            } else {
                displayEditActivityExceptionMessage();
            }
        } catch (CalorieCountException e) {
            displayCalorieCountOutOfBoundMessage();
        } catch (CalorieTagNotFoundException e) {
            displayCalorieTagNotFoundExceptionMessage();
        } catch (DescriptionLengthExceedException e) {
            displayDescriptionLengthExceedExceptionMessage();
        } catch (InvalidCalorieException e) {
            displayInvalidCalorieExceptionMessage();
        } catch (EmptyDescriptionException e) {
            displayEmptyDescriptionMessage();
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            displayEditActivityExceptionMessage();
        } catch (NumberFormatException e) {
            displayEditActivityExceptionMessage();
        } catch (Exception e) {
            displayEditActivityExceptionMessage();
        }
        return null;
    }

    /**
     * Checks if the input parameters of the user is valid.
     *
     * @param isDescriptionInputValid boolean variable annotating if the description is valid
     * @param isCalorieValid boolean variable annotating if the calorie count is valid
     * @throws Exception if either one of the boolean variable is false
     */
    protected void checkIfInputValuesForEditValid(boolean isDescriptionInputValid, boolean isCalorieValid)
            throws Exception {
        if (!(isCalorieValid && isDescriptionInputValid)) {
            throw new Exception();
        }
    }

    /**
     * Returns new activity description.
     *
     * @param userInput user input
     * @param calorieIndex index of calorie tag in user input
     * @return new activity description
     * @throws CalorieTagNotFoundException if the calorie tag is not found in user input
     */
    protected String getEditActivityDescription(String userInput, int calorieIndex) throws CalorieTagNotFoundException {
        String foodDescription;
        if (calorieIndex == INDEX_NOT_FOUND) {
            throw new CalorieTagNotFoundException();
        } else {
            foodDescription = userInput.substring(ALPHABET_WITH_SLASH_LENGTH, calorieIndex).trim();
        }
        return foodDescription;
    }

}
