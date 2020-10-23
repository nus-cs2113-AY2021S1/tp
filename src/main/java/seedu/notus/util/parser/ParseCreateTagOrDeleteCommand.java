package seedu.notus.util.parser;

//@@author Chongjx

import seedu.notus.command.Command;
import seedu.notus.command.CreateTagCommand;
import seedu.notus.command.DeleteTagCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.tag.Tag;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;

/**
 * Represents a parser object specifically to parse message for CreateTagCommand.
 */
public class ParseCreateTagOrDeleteCommand extends Parser {
    boolean isCreate;

    public ParseCreateTagOrDeleteCommand(String userMessage, boolean isCreate) {
        super(userMessage);
        this.isCreate = isCreate;
    }

    /**
     * Returns a CreateTagCommand or a DeleteTagCommand based on the user message.
     *
     * @return either a new CreateTagCommand or DeleteTagCommand.
     * @throws SystemException for missing tag prefix or tag name.
     */
    @Override
    public Command parse() throws SystemException {
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);

            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0];
                if (prefix.equalsIgnoreCase(PREFIX_TAG)) {
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                } else {
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
            // Ensures that there is at least 1 tag to be created or deleted.
            if (tags.isEmpty()) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG_PREFIX);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_TAG);
        }

        if (isCreate) {
            return new CreateTagCommand(tags);
        } else {
            return new DeleteTagCommand(tags);
        }
    }
}
