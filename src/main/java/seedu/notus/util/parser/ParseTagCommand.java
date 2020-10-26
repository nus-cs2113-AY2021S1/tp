package seedu.notus.util.parser;

//@@author Chongjx

import seedu.notus.command.Command;
import seedu.notus.command.TagEventCommand;
import seedu.notus.command.TagNoteCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.tag.Tag;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;

/**
 * Represents a parser object specifically to parse message for ParseTagNoteCommand.
 */
public class ParseTagCommand extends Parser {
    boolean isNote;

    public ParseTagCommand(String userMessage, boolean isNote) {
        super(userMessage);
        this.isNote = isNote;
    }

    /**
     * Parses the userMessage into an arrayList of tags that is be tagged or untagged from the object.
     *
     * @return Returns a TagCommand to be executed by NotUS.
     * @throws SystemException for invalid index input, missing tag prefix or tag name.
     */
    @Override
    public Command parse() throws SystemException {
        int index = NULL_INDEX;
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                switch (prefix) {
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    break;
                case PREFIX_INDEX:
                    ExceptionType exception = ExceptionType.EXCEPTION_MISSING_INDEX;
                    index = Integer.parseInt(checkBlank(infoDetails[1].trim(), exception));
                    if (index <= NULL_INDEX) {
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
                    }
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
            if (tags.isEmpty()) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG_PREFIX);
            }
            if (index <= NULL_INDEX) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX_PREFIX);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX);
        } catch (NumberFormatException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
        }

        if (isNote) {
            return new TagNoteCommand(index - 1, tags);
        } else {
            return new TagEventCommand(index - 1, tags);
        }
    }
}
