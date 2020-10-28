package seedu.notus.util.parser;

//@@author Narzyl
import seedu.notus.command.Command;
import seedu.notus.command.DeleteNoteCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Represents a parser object specifically to parse message for DeleteNoteCommand.
 */
public class ParseDeleteNoteCommand extends Parser {

    public ParseDeleteNoteCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * Parses the into an int or string for DeleteNoteCommand.
     *
     * @return Returns a DeleteNoteCommand to be executed by NotUS.
     * @throws SystemException if an error occurs.
     */
    @Override
    public Command parse() throws SystemException {
        int index;
        String title;
        String prefix;
        boolean isIndex = false;

        try {
            // Get prefix
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                prefix = infoDetails[0].toLowerCase();
                switch (prefix) {
                case PREFIX_INDEX:
                    isIndex = true;
                    index = Integer.parseInt(checkBlank(infoDetails[1], ExceptionType.EXCEPTION_MISSING_INDEX));

                    if (index <= NULL_INDEX) {
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
                    }
                    return new DeleteNoteCommand(index - 1);
                case PREFIX_TITLE:
                    title = checkBlank(infoDetails[1], ExceptionType.EXCEPTION_MISSING_TITLE);
                    return new DeleteNoteCommand(title);
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            if (isIndex) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
            } else {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE);
            }
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }
        throw new SystemException(ExceptionType.EXCEPTION_INVALID_INPUT_FORMAT);
    }
}
