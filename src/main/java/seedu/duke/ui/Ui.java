package seedu.duke.ui;


import static seedu.duke.ui.ExceptionMessages.print;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    //@@author e0425705
    /**
     * Prompts user to seek help if needed.
     */
    public static final String PROMPT_USER_OF_HELP_MESSAGE = "Please input 'help' for the commands "
            + "and their respective input format.";

    //@@author e0425705
    /**
     * Divider.
     */
    public static final String DIVIDER = "=========================================="
            + "==========================================";

    //@@author e0425705
    /**
     * Help list divider.
     */
    public static final String HELP_LIST_DIVIDER = "============================================================"
            + "========================================";

    /**
     * logo of application and the welcoming message.
     */
    private static final String LOGO =
            "| Hello from                                                                       |\n"
            + "|  _                  _  __   ___     _     _                                      |\n"
            + "| | |_   _ _   __ _  | |/ /  / __|   /_\\   | |                                     |\n"
            + "| |  _| | '_| / _` | | ' <  | (__   / _ \\  | |__                                   |\n"
            + "|  \\__| |_|   \\__,_| |_|\\_\\  \\___| /_/ \\_\\ |____|                                  |\n"
            + "|                                                                                  |\n"
            + "| Hello! I'm traKCAL.                                                              |\n"
            + "| " + PROMPT_USER_OF_HELP_MESSAGE + "          |";

    /**
     * Prints out welcome message to user when program is run.
     */
    public static void displayWelcomeMessage() {
        print(LOGO);
    }

    //@@author e0425705
    /**
     * Prints out the divider.
     */
    public static void drawDivider() {
        System.out.println(DIVIDER);
    }

    //@@author e0425705
    /**
     * Prints out help list showing the commands available.
     */
    public static void displayHelpMessage() {
        System.out.println(HELP_LIST_DIVIDER);
        String helpList = "This section displays the commands available and their respective input format.\n"
                + "> Words in CAPS are parameters to be filled in by you!\n"
                + "> Variables in <here> are optional!"
                + "\n"
                + "Viewing help:\n"
                + "help                   - Prints out commands available and their input format\n"
                + "\n"
                + "User Profiling\n"
                + "user l/                - Prints out current user profile\n"
                + "user c/                - Creates new user profile\n"
                + "user e/ <n/ NAME>, <g/ GENDER>, <w/ WEIGHT>, <h/ HEIGHT>, <age/ AGE>, <al/ ACTIVITY_FACTOR>,\n"
                + "                         <goal/ GOAL>\n"
                + "                       - Edits user profile to name, NAME, gender(male/female), GENDER,\n"
                + "                         height(in cm), HEIGHT, age, AGE, activity factor(1-5), ACTIVITY_FACTOR,\n"
                + "                         goal(lose/maintain/gain), GOAL\n"
                + "\n"
                + "Creating shortcut:\n"
                + "*[This command is extensive, there are a lot of variations. The following is one such example]*\n"
                + "createSet SHORTCUT_NAME f/ FOOD_DESCRIPTION c/ CALORIE_COUNT +\n"
                + "e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT + f/ FOOD_DESCRIPTION c/ CALORIE_COUNT\n"
                + "*[Another possible example is:]*\n"
                + "createSet SHORTCUT_NAME f/ FOOD_DESCRIPTION c/ CALORIE_COUNT + f/ FOOD_DESCRIPTION c/"
                + " CALORIE_COUNT\n"
                + "                       - Creates shortcut, SHORTCUT_NAME for adding food(s) and/or exercise(s)\n"
                + "                         depending on the format entered\n"
                + "addSet SHORTCUT_NAME   - Adds SHORTCUT_NAME that was created in createSet into today's list\n"
                + "\n"
                + "Adding:\n"
                + "add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT\n"
                + "                       - Adds food consumed, FOOD_DESCRIPTION and calories gained, CALORIE_COUNT\n"
                + "                         to today's date\n"
                + "add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE\n"
                + "                       - Adds food consumed, FOOD_DESCRIPTION, calories gained, CALORIE_COUNT\n"
                + "                         and date(YYYY-MM-DD), DATE\n"
                + "add e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT\n"
                + "                       - Adds exercise done, EXERCISE_DESCRIPTION and calories lost, CALORIE_COUNT\n"
                + "                         to today's date\n"
                + "add e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT d/ DATE\n"
                + "                       - Adds exercise done, EXERCISE_DESCRIPTION, calories lost, CALORIE_COUNT\n"
                + "                         and date(YYYY-MM-DD), DATE\n"
                + "\n"
                + "Listing:\n"
                + "list                   - Prints out the list of entries.\n"
                + "list DATE              - Prints out the list of entries for the date(YYYY-MM-DD), DATE\n"
                + "\n"
                + "Editing:\n"
                + "edit LIST_INDEX f/ FOOD_DESCRIPTION c/ CALORIE_COUNT\n"
                + "                       - Edits entry at index LIST_INDEX of latest list printed out\n"
                + "                         to food consumed, FOOD_DESCRIPTION, calories gained, CALORIE_COUNT\n"
                + "edit LIST_INDEX e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT\n"
                + "                       - Edits entry at index LIST_INDEX of latest list printed out\n"
                + "                         to exercise done, EXERCISE_DESCRIPTION, calories lost, CALORIE_COUNT\n"
                + "\n"
                + "Finding:\n"
                + "find d/ DESCRIPTION    - Searches for all entries description with the DESCRIPTION keyword\n"
                + "find c/ CALORIE_COUNT  - Searches for all entries with calories of CALORIE_COUNT\n"
                + "find a/ DESCRIPTION1 / DESCRIPTION2 ... / DESCRIPTION\n"
                + "                        - Searches for all entries with ALL matching keywords from\n"
                + "                          DESCRIPTION1 to DESCRIPTION\n"
                + "find e/ DESCRIPTION1 / DESCRIPTION2 ... / DESCRIPTION\n"
                + "                        - Searches for all entries with AT LEAST one matching keyword from\n"
                + "                          DESCRIPTION1 to DESCRIPTION\n"
                + "\n"
                + "Moving:\n"
                + "move from/ INDEX1 below/ INDEX2\n"
                + "                       - Moves the entry at index INDEX1 to the index below INDEX2\n"
                + "\n"
                + "Deleting:\n"
                + "delete LIST_INDEX      - Deletes entry located at index LIST_INDEX of latest list printed out\n"
                + "delete all/            - Deletes all entries in current date list\n"
                + "\n"
                + "Graphing:\n"
                + "graph                  - Generates a graph of target calorie and net calorie obtained up to\n"
                + "                         last 7 days\n"
                + "\n"
                + "Chaining:\n"
                + "*[This command is extensive, there are a lot of variations,\n"
                + "                          but is only available to add, list and edit.]*\n"
                + "*[One possible example is:]*\n"
                + "add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE && list && edit LIST_INDEX f/ FOOD_DESCRIPTION\n"
                + "add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE && list && edit LIST_INDEX f/ FOOD_DESCRIPTION\n"
                + "                         c/ CALORIE_COUNT\n"
                + "*[Another possible example is:]*\n"
                + "add f/ FOOD_DESCRIPTION c/ CALORIE_COUNT d/ DATE && add e/ EXERCISE_DESCRIPTION c/ CALORIE_COUNT\n"
                + "\n"
                + "Exiting:\n"
                + "bye                    - Terminates the application\n";
        System.out.print(helpList);
        System.out.println(HELP_LIST_DIVIDER);
    }

    //@@author e0425705
    /**
     * Prints out message when editing activity successful.
     */
    public static void displayEditMessage() {
        print("Noted! The following has been edited:");
    }

    //@@author e0425705
    /**
     * Prints out message when adding activity successful.
     */
    public static void displayAddMessage() {
        print("Noted! The following has been added into list:");
    }

    /**
     * Prints out acknowledgement of saving current activity list in file.
     */
    public static void displaySavedMessage() {
        print("The current activity list has been saved.");
    }

    /**
     * Prints out error in saving current activity list in file.
     */
    public static void displayNotSavedMessage() {
        print("The current activity list has not been saved.\n"
                + "An error has occurred!\n"
                + PROMPT_USER_OF_HELP_MESSAGE);
    }

    //@@author e0425705
    /**
     * Prints out bye message and let the user know that the current list has been saved to file.
     */
    public static void displayByeMessage() {
        print("| Thank you for using traKCAL. See you again!"
                + "                                      |");
    }

    /**
     * Prints out message when list command given but activity list is empty.
     */
    public static void displayEmptyActivityCounterMessage() {
        System.out.println("Nothing was added!");
    }

    /**
     * Prints out message asking for gender.
     */
    public static void displayAskUserGenderMessage() {
        print("What is your gender (male/female)?");
    }

    /**
     * Prints out message asking for weight goal.
     */
    public static void displayAskUserWeightGoalMessage() {
        print("Do you wish to lose/maintain/gain weight?");
    }

    /**
     * Prints out message asking for weight.
     */
    public static void displayAskUserWeightMessage() {
        print("What is your weight in kg?");
    }

    /**
     * Prints out message asking for height.
     */
    public static void displayAskUserHeightMessage() {

        print("What is your height in cm?");
    }

    /**
     * Prints out message asking for age.
     */
    public static void displayAskUserAgeMessage() {
        print("What is your age?");
    }

    /**
     * Prints out message asking for activity level.
     */
    public static void displayAskUserActivityLevelMessage() {
        print("How active are you on a scale of 1-5? With 1 being least active and 5 being most active.");
    }

    public static void displayMessage(String message) {
        drawDivider();
        System.out.println(message);
        drawDivider();
    }

    /**
     * Prints out edited name.
     */
    public static void displayEditNameMessage() {
        System.out.print("Your name has been updated to ");
    }

    /**
     * Prints out edited gender.
     */
    public static void displayEditGenderMessage() {
        System.out.print("Your gender has been updated to ");
    }

    /**
     * Prints out edited weight.
     */
    public static void displayEditWeightMessage() {
        System.out.print("Your weight has been updated to ");
    }

    /**
     * Prints out the edited height.
     */
    public static void displayEditHeightMessage() {
        System.out.print("Your height has been updated to ");
    }

    /**
     * Prints out the edited age.
     */
    public static void displayEditAgeMessage() {
        System.out.print("Your age has been updated to ");
    }

    /**
     * Prints out edited activity level.
     */
    public static void displayEditActivityLevelMessage() {
        System.out.print("Your activity level has been updated to ");
    }

    /**
     * Printed updated goal.
     */
    public static void displayEditGoalMessage() {
        System.out.print("Your weight goal has been updated to ");
    }

    /**
     * Prints out welcoming message to the user.
     */
    public static void displayReturningUserMessage() {
        print("Welcome! What would you like to do today?");
    }

    /**
     * Prints out message asking for name.
     */
    public static void displayAskUserNameMessage() {
        print("What is your name?");
    }

    /**
     * Prints out confirmation message for addSet.
     */
    public static void displayAddSetConfirmationMessage() {
        print("We are attempting to add activities listed in this shortcut.");
    }

}
