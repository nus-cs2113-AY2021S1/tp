package seedu.notus.util.parser;

//@@author Nazryl
import seedu.notus.command.AddNoteCommand;
import seedu.notus.command.Command;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.notebook.Note;
import seedu.notus.data.tag.Tag;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.*;

/**
 * Represents a parser object specifically to parse message for AddNoteCommand.
 */
public class ParseAddNoteCommand extends Parser {

    public ParseAddNoteCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * Parses userInput into Note before adding into Notebook.
     *
     * @return Returns an AddNoteCommand to be executed by NotUS.
     * @throws SystemException if an error occurs.
     */
    @Override
    public Command parse() throws SystemException {
        Note note;
        String title = "";
        ArrayList<String> content = new ArrayList<>();
        boolean isPinned = false;
        boolean isArchived = false;
        boolean isStored = false;
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            // Get prefix
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                switch (prefix) {
                case PREFIX_TITLE:
                    title = checkBlank(infoDetails[1], ExceptionType.EXCEPTION_MISSING_TITLE);
                    break;
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    break;
                case PREFIX_PIN:
                    isPinned = Boolean.parseBoolean(checkBlank(infoDetails[1], ExceptionType.EXCEPTION_MISSING_PIN));
                    break;
                case PREFIX_ARCHIVE:
                    isArchived = Boolean.parseBoolean(checkBlank(infoDetails[1],
                            ExceptionType.EXCEPTION_MISSING_ARCHIVE));
                    break;
                case PREFIX_LOAD:
                    isStored = true;
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
            title = checkBlank(title, ExceptionType.EXCEPTION_MISSING_TITLE_PREFIX);

            // Add to note
            note = tags.isEmpty() ? new Note(title, content, isPinned, isArchived) :
                    new Note(title, content, isPinned, isArchived, tags);

            return new AddNoteCommand(note, isStored);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TITLE);
        }
    }
}
