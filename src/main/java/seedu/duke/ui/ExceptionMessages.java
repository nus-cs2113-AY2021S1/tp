package seedu.duke.ui;

import static seedu.duke.ui.Ui.DIVIDER;
import static seedu.duke.ui.Ui.PROMPTUSEROFHELPMESSAGE;
import static seedu.duke.ui.Ui.drawDivider;

/**
 * Deals with error/exception messages.
 */
public class ExceptionMessages {
    /**
     * Prints error message when StringIndexOutOfBoundsException occurs.
     */
    public static void displayStringIndexOutOfBoundsExceptionMessage() {
        print("The index entered is not within the range!\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints error message when IOException occurs.
     */
    public static void displayIoExceptionMessage() {
        print("IO Exception has occurred!\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints error message when NumberFormatException occurs in delete command.
     */
    public static void displayDeleteCommandNumberFormatExceptionMessage() {
        print("Index is not a number!\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints error message when NullPointerException occurs in delete command.
     */
    public static void displayDeleteCommandNullPointerExceptionMessage() {
        print("There is not index to remove!\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints error message when attempting to access an empty list.
     */
    public static void displayListNotFoundExceptionMessage() {
        print("The list to work on is empty!\n"
                + "Try entering a list command first.\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints error message when NullPointerException occurs in Parser.
     */
    public static void displayParserNullPointerExceptionMessage() {
        print("Invalid command!\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints error message when error occurs in add command.
     */
    public static void displayAddCommandErrorMessage() {
        print("Invalid input given!\n"
                + "The input format for adding food activity is:\n"
                + "\tadd f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE\n"
                + "\t- where food description is FOOD_DESCRIPTION, calories consumed is CALORIE_COUNT\n"
                + "\tand date(YYYY-MM-DD) is DATE\n"
                + "\n"
                + "The input format for adding exercise activity is:\n"
                + "\tadd e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT d/ DATE\n"
                + "\t- where exercise description is EXERCISE_DESCRIPTION, calories lost is CALORIE_COUNT\n"
                + "\tand date(YYYY-MM-DD) is DATE\n"
                + "Do note that DATE is optional!\n"
                + "If you do not input it, the activity would be added to today's list!\n"
                + "If this is still too confusing for you, please input 'help' for more information.");
    }

    /**
     * Prints out error message when input of user is not understood.
     */
    public static String displayInvalidInputErrorMessage() {
        String invalidMessage = "Sorry I do not understand the input given!\n"
                + PROMPTUSEROFHELPMESSAGE;
        return invalidMessage;
    }

    /**
     * Prints error message when error occurs in find command.
     */
    public static void displayFindErrorMessage() {
        print("I do not understand what you mean!\n"
                + "The input format for finding food or exercise activity\n\tvia keyword is 'find d/ X'"
                + " - where keyword is X\n"
                + "The input format for finding calorie count\n\tvia keyword is 'find c/ X'"
                + " - where calories count is X\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints out error message when date and time format entered is wrong.
     */
    public static void displayIncorrectDateTimeFormatEnteredMessage() {
        print("Wrong format of date entered!\n"
                + "The accepted format is YYYY-MM-DD!\n"
                + "Also, ensure that the date input actually exists!\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints out error message when invalid input for add activity given.
     */
    public static void displayAddActivityExceptionMessage() {
        print("Sorry I do not understand what you mean!\n"
                + "One of the following has been violated:\n"
                + "\t1. Description and calorie count input cannot be empty\n"
                + "\t2. Calorie count input must be an integer > 0!\n"
                + "\t3. Wrong input format\n"
                + "\t\tInput format is:\n"
                + "\t\tadd f/ FOOD_DESCRIPTION c/ CALORIES d/ YYYY-MM-DD\n"
                + "\t\tadd e/ EXERCISE_DESCRIPTION c/ CALORIES d/ YYYY-MM-DD\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints out error message when invalid input for add activity given.
     */
    public static void displayEditActivityExceptionMessage() {
        print("Sorry I do not understand what you mean!\n"
                + "One of the following has been violated:\n"
                + "\t1. Description and calorie count input cannot be empty\n"
                + "\t2. Calorie count input must be an integer > 0!\n"
                + "\t3. Wrong input format\n"
                + "\t\tInput format is:\n"
                + "\t\tedita INDEX f/ FOOD_DESCRIPTION c/ CALORIES\n"
                + "\t\tedita INDEX e/ EXERCISE_DESCRIPTION c/ CALORIES\n"
                + PROMPTUSEROFHELPMESSAGE);
    }

    /**
     * Prints out error message when DateTimeException occurs.
     */
    public static void displayDateTimeExceptionMessage() {
        print("Sorry, I do not understand!\n"
                + "The input format of date is YYYY-MM-DD!");
    }

    /**
     * Prints out error message when attributes input by user is empty.
     */
    public static void displayEmptyEditActivityErrorMessage() {
        print("Sorry! There is missing input!");
    }

    /**
     * Prints message when there is a presence of a duplicated naming for file.
     */
    public static void displayExistingFileMessage() {
        print("Sorry! There is an existing set with this name, please use another name!");
    }

    /**
     * Prints out error message when invalid gender input.
     */
    public static void displayInvalidGenderMessage() {
        print("Please input female or male as gender only!");
    }

    /**
     * Prints out error message when invalid weight goal input.
     */
    public static void displayInvalidWeightGoalMessage() {
        print("Please input lose or maintain or gain as weight goal only!");
    }

    /**
     * Prints out error message when invalid weight input.
     */
    public static void displayInvalidWeightMessage() {
        print("Please enter a valid weight format!");
    }

    /**
     * Prints out error message when invalid height input.
     */
    public static void displayInvalidHeightMessage() {
        print("Please enter a valid height format!");
    }

    /**
     * Prints out error message when invalid age input.
     */
    public static void displayInvalidAgeMessage() {
        print("Please enter a valid age!");
    }

    /**
     * Prints out error message when invalid activity level input.
     */
    public static void displayInvalidActivityLevelMessage() {
        print("Sorry this is an invalid activity factor!\n"
                + "Please enter an integer from 1 to 5, with 1 being the least "
                + "active and 5 being the most active");
    }

    /**
     * Prints out error message when IndexOutOfBoundsException occurs in edit command.
     */
    public static void displayEditIndexOutOfBoundsExceptionMessage() {
        print("Index entered is not within the range!\n"
                + "Please pull out the list for the day before editing on it!");
    }

    /**
     * Prints out error message when a shortcut has not been created in addSet command.
     */
    public static void displayShortcutDoesNotExistMessage() {
        print("This shortcut does not exists, please create a shortcut before adding it!");
    }

    /**
     * Prints out error message when calorie has not been input as integer.
     */
    public static void displayInvalidCalorieEntryMessage() {
        print("Please enter calories as an integer");
    }

    /**
     * Prints out error message when weight range is not between 20 to 650kg.
     */
    public static void displayInvalidWeightRangeMessage() {
        print("Please enter a weight range from 20kg to 650kg");
    }

    /**
     * Prints out error message when age is not between 0 to 120 years old.
     */
    public static void displayInvalidAgeRangeMessage() {
        print("Please enter an age range from 1 to 120 years old");
    }

    /**
     * Prints out error message when height is not between 10 to 300cm.
     */
    public static void displayInvalidHeightRangeMessage() {
        print("Please enter a height range from 10cm to 300cm");
    }

    /**
     * Prints out error message when activity level from 1 to 5.
     */
    public static void displayInvalidActivityLevelRangeMessage() {
        print("Please enter an activity level from 1 to 5 only");
    }

    /**
     * Prints out error message when calorie count is out of the accepted bound of this application.
     */
    public static void displayCalorieCountOutOfBoundMessage() {
        String calorieOutOfBoundMessage = "Calorie count should be > 0 and <= 3000!\n"
                + "Please input a valid calorie count that is within the range!";
        print(calorieOutOfBoundMessage);
    }

    /**
     * Prints out error message when calorie tag not found.
     */
    public static void displayCalorieTagNotFoundExceptionMessage() {
        String calorieTagNotFoundExceptionMessage = "Calorie count tag[c/] is missing in your input!";
        print(calorieTagNotFoundExceptionMessage);
    }

    /**
     * Prints out error message when description length exceed limit.
     */
    public static void displayDescriptionLengthExceedExceptionMessage() {
        String descriptionLengthExceedExceptionMessage = "Maximum description length is 40 characters only!";
        print(descriptionLengthExceedExceptionMessage);
    }

    /**
     * Prints out error message when description input by user is empty.
     */
    public static void displayEmptyDescriptionMessage() {
        String emptyDescriptionMessage = "Current description is empty!\n"
                + "Please input a valid description that is not empty!";
        print(emptyDescriptionMessage);
    }

    /**
     * Prints out error message when date input exceeds range accepted.
     */
    public static void displayDateLimitExceptionMessage() {
        String dateLimitExceptionMessage = "You have exceeded the accepted date range!\n"
                + "Date input has to be from 2020-11-01 to current date!";
        print(dateLimitExceptionMessage);
    }

    /**
     * Prints out error message when invalid calorie count entered.
     */
    public static void displayInvalidCalorieExceptionMessage() {
        String invalidCalorieExceptionMessage = "Invalid calorie input!\n"
                + "Calorie count must be an integer!";
        print(invalidCalorieExceptionMessage);
    }

    /**
     * Prints out error message for invalid number for delete command.
     */
    public static void displayDeleteCommandStringOutOfBoundExceptionMessage() {
        print("Invalid Index!");
    }

    /**
     * Prints a message in a specified format.
     *
     * @param message is the message to be printed out
     */
    public static void print(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    /**
     * Prints message when string entered is empty.
     */
    public static void displayEmptyStringMessage() {
        System.out.println("Please do not enter an empty input");
    }

    public static void displayInvalidEditedUserProfileMessage() {
        drawDivider();
        System.out.println("You have edited the user profile text file to the wrong format.");
        System.out.println("Please create a new user profile. If not certain features like graph cannot be used");
        drawDivider();
        System.out.println("");
    }

    public static void displayExcessNumberOfArguments() {
        print("This command has no description!");
    }

    public static void displayShortageOfArguments() {
        print("This command has missing description!");
    }
}
