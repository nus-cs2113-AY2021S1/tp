package seedu.notus.command;

import seedu.notus.ui.Formatter;

import java.util.ArrayList;

import static seedu.notus.util.CommandMessage.LIST_TAG_MESSAGE;
import static seedu.notus.util.CommandMessage.NO_TAG_MESSAGE;

//@@author Chongjx
/**
 * Lists all the Tags.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "list-t";

    @Override
    public String execute() {
        ArrayList<String> executedResult = tagManager.getAllTagsName();

        if (executedResult == null) {
            return Formatter.formatString(NO_TAG_MESSAGE);
        } else {
            executedResult.add(0, LIST_TAG_MESSAGE);
        }
        return Formatter.formatString(executedResult, true);
    }
}
