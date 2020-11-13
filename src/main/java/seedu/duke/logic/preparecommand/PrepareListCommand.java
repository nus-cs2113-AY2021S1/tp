package seedu.duke.logic.preparecommand;

import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.displayIncorrectDateTimeFormatEnteredMessage;

//@@author chewyang
/**
 * Prepares list command.
 */
public class PrepareListCommand extends PrepareCommand {


    public PrepareListCommand(String[] description) {
        super(description);
    }

    /**
     * Prepares the list command by checking the userInput.
     *
     * @return ListCommand
     */
    @Override
    public Command prepareCommand() {

        if (description.length == 1) {
            return new ListCommand();
        } else {
            String dateString = description[1];
            try {
                LocalDate date = processDate(dateString);
                return new ListCommand(date);
            } catch (DateTimeParseException e) {
                displayIncorrectDateTimeFormatEnteredMessage();
                logging.writeToLogWarning("Date entered is of incorrect format");
                return null;
            }
        }
    }


}
