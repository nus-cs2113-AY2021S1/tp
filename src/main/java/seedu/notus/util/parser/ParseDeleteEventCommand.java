package seedu.notus.util.parser;

//@@author brandonywl

import seedu.notus.command.Command;
import seedu.notus.command.DeleteEventCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import java.util.ArrayList;

/**
 * Represents a parser object specifically to parse message for DeleteEventCommand.
 */
public class ParseDeleteEventCommand extends Parser {

    public ParseDeleteEventCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * Parses the variables in userMessage to a form that is used in DeleteEventCommand.
     *
     * @return Returns a DeleteEventCommand to be executed by Duke.
     * @throws SystemException When the index is not numeric (e.g. index = 1%s).
     */
    @Override
    public Command parse() throws SystemException {
        int index;

        splitInfoDetails(userMessage);

        try {
            index = Integer.parseInt(checkBlank(userMessage, ExceptionType.EXCEPTION_MISSING_INDEX));
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }

        if (index <= NULL_INDEX) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
        }

        // Convert from human-readable index to index in array.
        return new DeleteEventCommand(index - 1);
    }
}
