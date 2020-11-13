package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.command.DeleteByIndexCommand;

import static seedu.duke.ui.ExceptionMessages.displayIndexNotNumberExceptionMessage;

//@@author 1-Karthigeyan-1
/**
 * Prepares delete by index command.
 */
public class PrepareDeleteByIndexCommand extends PrepareCommand {


    /**
     * Initializes PrepareDeleteByIndexCommand.
     *
     * @param description list of description parsed from parser.
     */
    public PrepareDeleteByIndexCommand(String[] description) {
        super(description);
    }

    /**
     * Checks validity of delete by index command.
     *
     * @return delete by index command.
     */
    @Override
    public Command prepareCommand() {
        try {
            int index = Integer.parseInt(description[1]) - 1;
            checkIndex(index);
            return new DeleteByIndexCommand(index);
        } catch (NumberFormatException e) {
            displayIndexNotNumberExceptionMessage();
        }
        return null;
    }
}
