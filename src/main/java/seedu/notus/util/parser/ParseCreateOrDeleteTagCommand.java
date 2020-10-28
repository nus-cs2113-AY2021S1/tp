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
public class ParseCreateOrDeleteTagCommand extends Parser {
    boolean isCreate;

    public ParseCreateOrDeleteTagCommand(String userMessage, boolean isCreate) {
        super(userMessage);
        this.isCreate = isCreate;
    }

    /**
     * Parses the userMessage to an arrayList of tags that is to be created or deleted.
     *
     * @return Returns a CreateTagCommand or DeleteTagCommand to be executed by NotUS.
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
