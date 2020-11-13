package seedu.duke.logic.preparecommand;

import seedu.duke.Trakcal;
import seedu.duke.command.Command;
import seedu.duke.command.GraphCommand;
import seedu.duke.exception.InvalidNumberOfArgumentsException;
import seedu.duke.exception.ListNotFoundException;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayEmptyListError;
import static seedu.duke.ui.ExceptionMessages.displayExcessNumberOfArguments;

//@@author 1-Karthigeyan-1
/**
 * Prepares graph command.
 */
public class PrepareGraphCommand extends PrepareCommand {
    protected static final int ARGUMENT_LIMIT = 1;

    public PrepareGraphCommand(String[] description) {
        super(description);
    }

    /**
     * Prepares the arguments needed for the graph command.
     *
     * @return graphCommand
     * @throws Exception if no records are found
     */
    public Command prepareCommand() {
        try {
            isNumberOfArgumentsValid(ARGUMENT_LIMIT);
            isListEmpty();
            return new GraphCommand();
        } catch (InvalidNumberOfArgumentsException e) {
            logging.writeToLogInfo("Too many arguments!");
            displayExcessNumberOfArguments();
        } catch (ListNotFoundException e) {
            displayEmptyListError();
            logging.writeToLogInfo("List is empty.");
        }
        return null;
    }

    /**
     * Checks if the list is empty.
     *
     * @return false if list is not empty
     * @throws ListNotFoundException if list is empty or not found.
     */
    public boolean isListEmpty() throws ListNotFoundException {
        if (Trakcal.calList.getHashMap().size() == 0) {
            throw new ListNotFoundException();
        }
        return false;
    }

}
