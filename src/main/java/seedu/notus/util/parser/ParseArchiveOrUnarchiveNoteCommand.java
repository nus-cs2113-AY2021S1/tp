package seedu.notus.util.parser;

//@@author R-Ramana

import seedu.notus.command.ArchiveNoteCommand;
import seedu.notus.command.Command;
import seedu.notus.command.UnarchiveNoteCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Represents a parser object specifically to parse message for ArchiveNoteCommand or UnarchiveNoteCommand.
 */
public class ParseArchiveOrUnarchiveNoteCommand extends Parser {

    boolean isArchive;

    public ParseArchiveOrUnarchiveNoteCommand(String userMessage, boolean isArchive) {
        super(userMessage);
        this.isArchive = isArchive;
    }

    /**
     * Prepares userInput before archiving.
     * User can archive or unarchive either via integer value of index or String value of title.
     *
     * @return Returns an ArchiveNoteCommand or UnarchiveNoteCommand to be executed by NotUS.
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
                    if (isArchive) {
                        return new ArchiveNoteCommand(index - 1);
                    } else {
                        return new UnarchiveNoteCommand(index - 1);
                    }
                case PREFIX_TITLE:
                    title = checkBlank(infoDetails[1], ExceptionType.EXCEPTION_MISSING_TITLE);

                    if (isArchive) {
                        return new ArchiveNoteCommand(title);
                    } else {
                        return new UnarchiveNoteCommand(title);
                    }
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
