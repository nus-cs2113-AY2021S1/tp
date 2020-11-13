package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.command.EditUserProfileCommand;
import seedu.duke.exception.InvalidNumberOfArgumentsException;


import java.io.IOException;

import static seedu.duke.ui.ExceptionMessages.displayShortageOfArguments;

/**
 * Prepares editing of user profile.
 */
public class PrepareEditUserProfileCommand extends PrepareCommand {

    public PrepareEditUserProfileCommand(String[] description) {
        super(description);
    }

    /**
     * Prepares to edit user profile.
     *
     * @return EditUserProfileCommand
     * @throws IOException when there is an error
     */
    public Command prepareCommand() {
        try {
            isNumberOfArgumentsValid(2);
            return new EditUserProfileCommand(description[1]);
        } catch (InvalidNumberOfArgumentsException e) {
            displayShortageOfArguments();
        }
        return null;
    }

}
