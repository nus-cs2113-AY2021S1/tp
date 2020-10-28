package seedu.notus.util.parser;

//@@author brandonywl

import seedu.notus.command.Command;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

/**
 * Represents a parser object specifically to parse message for EditEventCommand.
 */
public class ParseEditEventCommand extends Parser {

    public ParseEditEventCommand(String userMessage) {
        super(userMessage);
    }

    @Override
    public Command parse() throws SystemException {
        return null;
    }
}
