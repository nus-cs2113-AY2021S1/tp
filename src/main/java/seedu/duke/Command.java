package seedu.duke;

import seedu.duke.backend.UserInput;

public abstract class Command {

    public static final int NO_MATCH = -1;
    public static final int ARGUMENT_ERR = 0;
    public static final int ACCEPT = 1;
    private UserInput savedInput; // Variable for saving the given user input

    /**
     * Checks if the supplied userInput is valid for the command.
     * UserInput can contain any information so it's the command's job to ensure it is correct before acceptance
     * Once accepted, the command should store a copy of the UserInput for execution
     * @param input The UserInput to Validate
     * @return NO_MATCH if the command and category is incorrect, ARGUMENT_ERR if the argument supplied is incorrect.
     *          ACCEPT if the userInput is intended and correct for invoking this command.
     */
    public int validate(UserInput input) {
        return NO_MATCH;
    }

    /**
     * Runs the actual command processing.
     * @return String message to be printed to the user.
     */
    public abstract String execute();

    /**
     * Provides information about command usage.
     * @return The string to be printed to the user.
     */
    public abstract String help();
}
