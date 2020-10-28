package seedu.notus.util.parser;

//@@author R-Ramana

import seedu.notus.command.Command;
import seedu.notus.command.ListNoteCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_ARCHIVE;
import static seedu.notus.util.PrefixSyntax.PREFIX_SORT;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;
import static seedu.notus.util.PrefixSyntax.STRING_SORT_ASCENDING;
import static seedu.notus.util.PrefixSyntax.STRING_SORT_DESCENDING;

/**
 * Represents a parser object specifically to parse message for ListNoteCommand.
 */
public class ParseListNoteCommand extends Parser {

    public ParseListNoteCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * Returns a ListNote Command.
     * ListNoteCommand is overloaded, so depending on the user input i.e
     * list-n /tag TAG up/down
     * tags and up/down are optional parameters for users to input
     * up/down is to sort the list alphabetically either A-Z or Z-A
     *
     * @return Returns a ListNoteCommand to be executed by NotUS.
     */
    @Override
    public Command parse() throws SystemException {
        // If no optional parameters, return default display of list note
        if (userMessage == null) {
            return new ListNoteCommand();
        }

        String tagName;
        String sort;
        boolean isArchive = false;
        Boolean isAscending = null;
        ArrayList<String> tagsName = new ArrayList<>();
        boolean isTag = false;

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;

                switch (prefix) {
                case PREFIX_TAG:
                    isTag = true;
                    exception = ExceptionType.EXCEPTION_MISSING_TAG;
                    tagName = checkBlank(infoDetails[1], exception);
                    tagsName.add(tagName);
                    break;
                case PREFIX_SORT:
                    isTag = false;
                    exception = ExceptionType.EXCEPTION_MISSING_SORT;
                    sort = checkBlank(infoDetails[1], exception);
                    if (sort.equalsIgnoreCase(STRING_SORT_ASCENDING)) {
                        isAscending = true;
                    } else if (sort.equalsIgnoreCase(STRING_SORT_DESCENDING)) {
                        isAscending = false;
                    } else {
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_SORT_TYPE);
                    }
                    break;
                case PREFIX_ARCHIVE:
                    isTag = false;
                    isArchive = true;
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            if (isTag) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG);
            } else {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_SORT);
            }
        }

        if (isArchive) {
            return new ListNoteCommand(true);
        }

        // No optional parameters case as it is already accounted
        // Minimally if no tag, will have up/down and vice versa
        if (tagsName.isEmpty() && isAscending == null) {
            return new ListNoteCommand();
        } else if (tagsName.isEmpty() && isAscending != null) {
            return new ListNoteCommand(isAscending);
        } else if (!tagsName.isEmpty() && isAscending == null) {
            return new ListNoteCommand(tagsName);
        } else {
            return new ListNoteCommand(isAscending, tagsName);
        }
    }
}
