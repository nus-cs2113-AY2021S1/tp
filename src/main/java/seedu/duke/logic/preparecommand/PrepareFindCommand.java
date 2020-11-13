package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.command.FindAllCommand;
import seedu.duke.command.FindCalorieCommand;
import seedu.duke.command.FindDescriptionCommand;
import seedu.duke.command.FindEitherCommand;
import seedu.duke.exception.InvalidNumberOfArgumentsException;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayFindErrorMessage;
import static seedu.duke.ui.ExceptionMessages.displayShortageOfArguments;

//@@author poonchuanan
/**
 * Prepares find command.
 */
public class PrepareFindCommand extends PrepareCommand {
    protected static final String DESCRIPTION_TAG = "d/";
    protected static final String CALORIE_TAG = "c/";
    protected static final String ALL_TAG = "a/";
    protected static final String EITHER_TAG = "e/";

    public PrepareFindCommand(String[] description) {
        super(description);
    }


    /**
     * Prepares the find command by checking the userInput.
     * If the keyword contains activity description, returns FindDescriptionCommand.
     * Else if the keyword contains calories count, returns FindCalorieCommand.
     *
     * @return FindCalorieCommand
     */
    @Override
    public Command prepareCommand() {
        try {
            isNumberOfArgumentsValid(2);
            if (description[1].startsWith(DESCRIPTION_TAG)) {
                String descriptionString = description[1].substring(2).trim();
                return new FindDescriptionCommand(descriptionString);
            } else if (description[1].startsWith(CALORIE_TAG)) {
                String calorie = description[1].substring(2).trim();
                return new FindCalorieCommand(calorie);
            } else if (description[1].startsWith(ALL_TAG)) {
                return new FindAllCommand(description[1]);
            } else if (description[1].startsWith(EITHER_TAG)) {
                return new FindEitherCommand(description[1]);
            } else {
                displayFindErrorMessage();
            }
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            displayFindErrorMessage();
            logging.writeToLogWarning("Wrong index for find");
        } catch (InvalidNumberOfArgumentsException e) {
            displayShortageOfArguments();
            logging.writeToLogWarning("Wrong number of arguments for find");
        }
        return null;
    }
}
