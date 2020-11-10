package seedu.notus.util.parser;

//@@author prachi2023

import seedu.notus.command.Command;
import seedu.notus.command.ViewNoteCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Represents a parser object specifically to parse message for ParseViewNoteCommand.
 */
public class ParseViewNoteCommand extends Parser {

    public ParseViewNoteCommand(String userMessage) {
        super(userMessage);
    }

    @Override
    public Command parse() throws SystemException {
        String title;
        int index;
        boolean isTitle = false;

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;
                switch (prefix) {
                case PREFIX_TITLE:
                    isTitle = true;
                    exception = ExceptionType.EXCEPTION_MISSING_TITLE;
                    title = checkBlank(infoDetails[1],exception);
                    return new ViewNoteCommand(title);
                case PREFIX_INDEX:
                    isTitle = false;
                    exception = ExceptionType.EXCEPTION_MISSING_INDEX;
                    index = Integer.parseInt(checkBlank(infoDetails[1], exception));
                    if (index <= NULL_INDEX) {
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
                    }
                    return new ViewNoteCommand(index - 1);
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            if (isTitle) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE);
            } else {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
            }
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }
        throw new SystemException(ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
    }
}
