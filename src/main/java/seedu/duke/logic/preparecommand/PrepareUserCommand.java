package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.exception.InvalidNumberOfArgumentsException;
import static seedu.duke.ui.ExceptionMessages.displayInvalidEditUserProfileMessage;

import static seedu.duke.ui.ExceptionMessages.displayShortageOfArguments;

/**
 * Prepares user command.
 */
public class PrepareUserCommand extends PrepareCommand {
    private static final int ARGUMENT_LIMIT = 2;

    public PrepareUserCommand(String[] description) {
        super(description);
    }

    /**
     * Check validity for user commands.
     *
     * @return command depending on the description.
     */
    @Override
    public Command prepareCommand() {
        try {
            isNumberOfArgumentsValid(ARGUMENT_LIMIT);
        } catch (InvalidNumberOfArgumentsException e) {
            displayShortageOfArguments();
        }
        String[] input = description[1].split(" ", 2);

        switch (input[0].trim()) {
        case "l/":
            return new PrepareUserListCommand(input).prepareCommand();
        case "c/":
            return new PrepareCreateNewUserCommand(input).prepareCommand();
        case "e/":
            return new PrepareEditUserProfileCommand(input).prepareCommand();
        default:
            displayInvalidEditUserProfileMessage();
            return null;
        }
    }


}
