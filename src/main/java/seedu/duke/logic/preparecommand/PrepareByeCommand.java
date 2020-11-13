package seedu.duke.logic.preparecommand;

import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.exception.InvalidNumberOfArgumentsException;

import static seedu.duke.ui.ExceptionMessages.displayExcessNumberOfArguments;

/**
 * Prepares bye command.
 */
public class PrepareByeCommand extends PrepareCommand {
    protected static final int ARGUMENT_LIMIT = 1;

    public PrepareByeCommand(String[] description) {
        super(description);
    }

    /**
     * Checks validity of bye command.
     *
     * @return byecommand
     */
    @Override
    public Command prepareCommand() {
        try {
            isNumberOfArgumentsValid(ARGUMENT_LIMIT);
            return new ByeCommand();
        } catch (InvalidNumberOfArgumentsException e) {
            displayExcessNumberOfArguments();
        }
        return null;
    }
}
