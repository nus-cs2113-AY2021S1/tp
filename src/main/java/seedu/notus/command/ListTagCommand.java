package seedu.notus.command;

import seedu.notus.ui.Formatter;

import java.util.ArrayList;

//@@author Chongjx
/**
 * Lists all the Tags.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "list-t";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Lists all the tags.";

    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "There are no tags!";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Here are the list of tags!";

    @Override
    public String execute() {
        ArrayList<String> executedResult = tagManager.getAllTagsName();

        if (executedResult == null) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        } else {
            executedResult.add(0, COMMAND_SUCCESSFUL_MESSAGE);
        }
        return Formatter.formatString(executedResult, true);
    }
}
