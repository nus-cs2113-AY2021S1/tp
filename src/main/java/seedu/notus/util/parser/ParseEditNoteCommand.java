package seedu.notus.util.parser;

//@@author Nazryl

import seedu.notus.command.Command;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

/**
 * Represents a parser object specifically to parse message for EditNoteCommand.
 */
public class ParseEditNoteCommand extends Parser {

    public ParseEditNoteCommand(String userMessage) {
        super(userMessage);
    }

    @Override
    public Command parse() throws SystemException {
        return null;
    }
}
