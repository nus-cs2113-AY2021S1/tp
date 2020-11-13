package seedu.duke.ui;

import static seedu.duke.ui.Ui.DIVIDER;
import static seedu.duke.ui.Ui.PROMPT_USER_OF_HELP_MESSAGE;
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
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    /**
     * Prints error message when IOException occurs.
     */
    public static void displayIoExceptionMessage() {
        print("IO Exception has occurred!\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    /**
     * Prints error message when the user does not have logging permission.
     */
    public static void displaySecurityExceptionMessage() {
        print("Sorry! It appears you do not have logging permissions!\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    /**
     * Prints error message when NumberFormatException occurs in delete command.
     */
    public static void displayIndexNotNumberExceptionMessage() {
        print("Index is not a number!\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    public static void sameIndexForMoveCommandExceptionMessage() {
        print("The indexes entered for this move command does not result in any change!");
    }


    /**
     * Prints error message when NullPointerException occurs in delete command.
     */
    public static void displayDeleteCommandNullPointerExceptionMessage() {
        print("There is no index to remove!\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    /**
     * Prints error message when attempting to access an empty list.
     */
    public static void displayListNotFoundExceptionMessage() {
        print("The list to work on is empty!\n"
                + "Try entering a list command first.\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    //@@author e0425705
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
                + PROMPT_USER_OF_HELP_MESSAGE;

        return invalidMessage;
    }

    /**
     * Prints error message when error occurs in find command.
     */
    public static void displayFindErrorMessage() {
        print("I do not understand what you mean!\n"
                + "The input format for finding food or exercise activity\n\tvia keyword is 'find d/ DESCRIPTION'"
                + " - where keyword is DESCRIPTION\n"
                + "The input format for finding calorie count via keyword is 'find c/ CALORIE_COUNT'\n"
                + "\t- where calories count is CALORIE_COUNT\n"
                + "The input format for finding (all) is 'find a/ KEYWORD1 / KEYWORD2...'\n"
                + "\t- where KEYWORDS are all the words that matches the search result\n"
                + "The input format for finding (either) is 'find e/ KEYWORD1 / KEYWORD2...'\n"
                + "\t- where just one KEYWORD has to match the search result\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    //@@author e0425705
    /**
     * Prints out error message when date and time format entered is wrong.
     */
    public static void displayIncorrectDateTimeFormatEnteredMessage() {
        print("Wrong format of date entered!\n"
                + "The accepted format is YYYY-MM-DD!\n"
                + "Also, ensure that the date input actually exists!\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    //@@author e0425705
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
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    //@@author e0425705
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
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    //@@author e0425705
    /**
     * Prints out error message when DateTimeException occurs.
     */
    public static void displayDateTimeExceptionMessage() {
        print("Sorry, I do not understand!\n"
                + "The input format of date is YYYY-MM-DD!\n");
    }

    //@@author jlifah
    /**
     * Prints out error message when invalid gender input.
     */
    public static void displayInvalidGenderMessage() {
        print("Sorry this is an invalid gender format!\nPlease input gender as female or male only!");
    }

    /**
     * Prints out error message when invalid weight goal input.
     */
    public static void displayInvalidWeightGoalMessage() {
        print("Please input lose/maintain/gain as weight goal only!");
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
        print("Please enter a valid age format!");
    }

    /**
     * Prints out error message when invalid activity level input.
     */
    public static void displayInvalidActivityLevelMessage() {
        print("Sorry this is an invalid activity factor!\n"
                + "Please enter an integer from 1 to 5, with 1 being the least active and 5 being the most active");
    }
    //@@author jlifah

    //@@author e0425705
    /**
     * Prints out error message when IndexOutOfBoundsException occurs in edit command.
     */
    public static void displayEditIndexOutOfBoundsExceptionMessage() {
        print("Index entered is not within the range!\n"
                + "Please pull out the list for the day before editing on it!");
    }
    //@@author e0425705

    //@@author jlifah
    /**
     * Prints out error message when a shortcut has not been created in addSet command.
     */
    public static void displayShortcutDoesNotExistMessage() {
        print("This shortcut does not exists!\n"
                + "Please create a shortcut before adding it!");
    }

    /**
     * Prints out error message when weight range is not between 20 to 650kg.
     */
    public static void displayInvalidWeightRangeMessage() {
        print("Please enter a weight range from 30kg to 650kg");
    }

    /**
     * Prints out error message when age is not between 0 to 120 years old.
     *
     */
    public static void displayInvalidAgeRangeMessage() {
        print("Please enter an age range from 10 to 120 years old");
    }

    /**
     * Prints out error message when height is not between 10 to 300cm.
     *
     */
    public static void displayInvalidHeightRangeMessage() {
        print("Please enter a height range from 90cm to 300cm");
    }

    /**
     * Prints out error message when activity level from 1 to 5.
     */
    public static void displayInvalidActivityLevelRangeMessage() {
        print("Please enter an activity level from 1 to 5 only");
    }
    //@@author jlifah

    //@@author e0425705
    /**
     * Prints out error message when calorie count is out of the accepted bound of this application.
     */
    public static void displayCalorieCountOutOfBoundMessage() {
        String calorieOutOfBoundMessage = "Calorie count should be > 0 and <= 3000!\n"
                + "Please input a valid calorie count that is within the range!";
        print(calorieOutOfBoundMessage);
    }

    //@@author e0425705
    /**
     * Prints out error message when calorie tag not found.
     */
    public static void displayCalorieTagNotFoundExceptionMessage() {
        String calorieTagNotFoundExceptionMessage = "Calorie count tag[c/] is missing in your input!";
        print(calorieTagNotFoundExceptionMessage);
    }

    //@@author e0425705
    /**
     * Prints out error message when description length exceed limit.
     */
    public static void displayDescriptionLengthExceedExceptionMessage() {
        String descriptionLengthExceedExceptionMessage = "Maximum description length is 40 characters only!";
        print(descriptionLengthExceedExceptionMessage);
    }

    //@@author e0425705
    /**
     * Prints out error message when description input by user is empty.
     */
    public static void displayEmptyDescriptionMessage() {
        String emptyDescriptionMessage = "Current description is empty!\n"
                + "Please input a valid description that is not empty!";
        print(emptyDescriptionMessage);
    }

    //@@author e0425705
    /**
     * Prints out error message when date input exceeds range accepted.
     */
    public static void displayDateLimitExceptionMessage() {
        String dateLimitExceptionMessage = "You have exceeded the accepted date range!\n"
                + "Date input has to be from 2020-10-14 to current date!";
        print(dateLimitExceptionMessage);
    }

    //@@author e0425705
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

    //@@author jlifah
    /**
     * Prints message when string entered is empty.
     */
    public static void displayEmptyStringMessage() {
        print("Please do not enter an empty input");
    }

    /**
     * Prints message when txt file read from is corrupted.
     */
    public static void displayInvalidEditedUserProfileMessage() {
        drawDivider();
        System.out.println("Corrupted user profile!");
        System.out.println("Please do not edit the text file unnecessarily.");
        System.out.println("You are being directed to create a new user profile.");
        System.out.println("This is to ensure that all features can be used properly.");
        drawDivider();
        System.out.println();
    }

    /**
     * Prints message when shortcut of similar name already exists.
     */
    public static void displayExistingShortcutMessage() {
        drawDivider();
        System.out.println("There is a shortcut with this name already. Please choose another name");
        drawDivider();
    }

    /**
     * Prints message when missing calorie tag and/or activity tag.
     */
    public static void displayInvalidCreateSetCommandMessage() {
        drawDivider();
        System.out.println("You are missing an activity tag or calorie tag or both");
        drawDivider();
    }

    /**
     * Prints message when calories is not within range.
     */
    public static void displayInvalidCalorieMessage() {
        drawDivider();
        System.out.println("Please enter a valid calorie range.");
        drawDivider();
    }

    /**
     * Prints message user did not enter type integer as calorie.
     */
    public static void displayCalorieMustBeIntegerMessage() {
        drawDivider();
        System.out.println("Please enter calorie as integer.");
        drawDivider();
    }

    /**
     * Prints message for wrongly edited set text file.
     */
    public static void displayIncompleteSetMessage() {
        drawDivider();
        System.out.println("This shortcut was not successfully created as there was an error in your input.");
        System.out.println("Please try again!");
        drawDivider();
        System.out.println();
    }

    /**
     * Prints message when user did not specify shortcut name.
     */
    public static void displayMissingFileNameMessage() {
        drawDivider();
        System.out.println("You did not specify a shortcut name in your createSet command.");
        System.out.println("As such, no shortcut was created. Please try again!");
        drawDivider();
        System.out.println();
    }

    /**
     * Prints corrupted shortcut message.
     *
     */
    public static void displayCorruptedSetMessage() {
        drawDivider();
        System.out.println("Your shortcut text file is corrupted!");
        System.out.println("As such, it will now be deleted.\n Please avoid editing the text file to the "
                + "wrong format in the future.");
        System.out.println("Items in the shortcut may have been added partially only.");
        drawDivider();
        System.out.println();
    }

    /**
     * Prints out empty description for activity of calories tag message.
     */
    public static void displayMissingAddSetInfoMessage() {
        drawDivider();
        System.out.println("This shortcut has an empty description for activity or calorie or both.");
        drawDivider();
    }

    /**
     * Prints message when edit user profile command is invalid.
     */
    public static void displayInvalidEditCommandMessage() {
        print("I do not understand all or part of this edit command. Your command has only been processed partially.");
    }
    //@@author jlifah

    /**
     * Prints message when there is too many arguments.
     */
    public static void displayExcessNumberOfArguments() {
        print("This command has no description!");
    }

    /**
     * Prints message when there is too little arguments.
     */
    public static void displayShortageOfArguments() {
        print("This command has missing description!");
    }

    /**
     * Prints message when the list is empty.
     */
    public static void displayEmptyListError() {
        print("List is empty!");
    }

    /**
     * Prints message when traKCAL is unable to write/read file.
     */
    public static void displayFileError() {
        print("Unable to read/write file");
    }

    /**
     * Prints message when find command finds no results.
     */
    public static void displayKeywordNotFoundMessage() {
        print("No results were found!");
    }

    /**
     * Prints message when there are consecutive slashes in input.
     */
    public static void displayEmptyKeywordMessage() {
        print("Current keyword is empty!\n"
                + "Please input a valid keyword that is not empty!");
    }

    /**
     * Prints message when there are consecutive slashes in input.
     */
    public static void displayFindSlashExceptionMessage() {
        print("There are consecutive slashes in your input!");
    }

    /**
     * Prints message when there are consecutive slashes in input.
     */
    public static void displayInvalidEditUserProfileMessage() {
        print("I do not understand this edit user profile command!");
    }
}
