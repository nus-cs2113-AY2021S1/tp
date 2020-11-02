package seedu.notus.util.parser;

//@@author Nazryl

import seedu.notus.command.Command;
import seedu.notus.command.EditNoteCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.notebook.Note;
import seedu.notus.data.tag.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static seedu.notus.util.PrefixSyntax.PREFIX_ADD_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_CONTENT;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELETE_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Represents a parser object specifically to parse message for EditNoteCommand.
 */
public class ParseEditNoteCommand extends Parser {

    public ParseEditNoteCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * Parses userInput into Note before editing into Notebook.
     *
     * @return Result of the add note command.
     * @throws SystemException if an error occurs.
     */
    @Override
    public Command parse() throws SystemException {
        int index = 0;

        String prefixClashMode = "";
        String title = "";

        boolean isArchived = false;
        boolean isInput = false;
        boolean isChangesCheck = false;

        Note note;

        ArrayList<String> content = new ArrayList<>();
        ArrayList<Tag> tags = new ArrayList<>();

        Map<Integer, String> addLists = new HashMap<>();
        Map<Integer, String> editLists = new HashMap<>();
        Map<Integer, String> deleteLists = new HashMap<>();

        try {
            // Get prefix
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                switch (prefix) {
                case PREFIX_INDEX:
                    index = Integer.parseInt(checkBlank(infoDetails[1], ExceptionType.EXCEPTION_MISSING_INDEX));
                    if (index <= NULL_INDEX) {
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
                    }
                    break;
                case PREFIX_TITLE:
                    title = checkBlank(infoDetails[1], ExceptionType.EXCEPTION_MISSING_TITLE);
                    isChangesCheck = true;
                    break;
                case PREFIX_ADD_LINE:
                    if (prefixClashMode.isBlank() || prefixClashMode.equals(PREFIX_ADD_LINE)) {
                        addLists = addToLists(PREFIX_ADD_LINE, addLists, infoDetails[1]);
                        addLists = sortByKey(addLists);
                        prefixClashMode = PREFIX_ADD_LINE;
                        isChangesCheck = true;
                    } else {
                        throw new SystemException(ExceptionType.EXCEPTION_CLASH_FORMAT);
                    }
                    break;
                case PREFIX_LINE:
                    if (prefixClashMode.isBlank() || prefixClashMode.equals(PREFIX_LINE)) {
                        editLists = addToLists(PREFIX_LINE, editLists, infoDetails[1]);
                        prefixClashMode = PREFIX_LINE;
                        isChangesCheck = true;
                    } else {
                        throw new SystemException(ExceptionType.EXCEPTION_CLASH_FORMAT);
                    }
                    break;
                case PREFIX_DELETE_LINE:
                    if (prefixClashMode.isBlank() || prefixClashMode.equals(PREFIX_DELETE_LINE)) {
                        deleteLists = addToLists(PREFIX_DELETE_LINE, deleteLists, infoDetails[1]);
                        deleteLists = sortByKey(deleteLists);
                        prefixClashMode = PREFIX_DELETE_LINE;
                        isChangesCheck = true;
                    } else {
                        throw new SystemException(ExceptionType.EXCEPTION_CLASH_FORMAT);
                    }
                    break;
                case PREFIX_CONTENT:
                    isInput = true;
                    isChangesCheck = true;
                    break;
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    isChangesCheck = true;
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }

            if (!isChangesCheck) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_PREFIX);
            }

            if (index <= NULL_INDEX) {
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
            }
            note = tags.isEmpty() ? new Note(title, content, null, isArchived) :
                    new Note(title, content, null, isArchived, tags);

            return new EditNoteCommand(index - 1, note, addLists, editLists, deleteLists, isInput);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX_PREFIX);
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }
    }
}
