package seedu.duke.logic.preparecommand;

import seedu.duke.command.AddSetCommand;
import seedu.duke.command.Command;
import seedu.duke.exception.InvalidNumberOfArgumentsException;

import static seedu.duke.ui.ExceptionMessages.displayShortageOfArguments;

/**
 * Prepares add set command.
 */
public class PrepareAddSetCommand extends PrepareCommand {
    protected static final int ARGUMENT_LIMIT = 2;

    public PrepareAddSetCommand(String[] description) {
        super(description);
    }

    /**
     * Prepares file to be read from and added into the current list.
     *
     * @return AddSet Command
     */
    @Override
    public Command prepareCommand() {
        try {
            isNumberOfArgumentsValid(ARGUMENT_LIMIT);
            return new AddSetCommand(description[1]);
        } catch (InvalidNumberOfArgumentsException e) {
            displayShortageOfArguments();
        }
        return null;
    }
}
