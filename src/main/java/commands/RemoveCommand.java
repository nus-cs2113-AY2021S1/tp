package commands;

import access.Access;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public abstract class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MODULE_PARAMETER = "MODULE_INDEX";
    public static final String CHAPTER_PARAMETER = "CHAPTER_INDEX";
    public static final String CARD_PARAMETER = "FLASHCARD_INDEX";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes module / chapter / flashcard based on the index in the list. \n"
            + "Parameters: " + MODULE_PARAMETER + "\n"
            + "            " + CHAPTER_PARAMETER + "\n"
            + "            " + CARD_PARAMETER + "\n" + "Example: " + COMMAND_WORD + " 2\n";

    protected static final String MESSAGE_SUCCESS = "Got it. I've removed this %1$s:\n";
    protected static final String MESSAGE_COUNT = "Now you have %1$d %2$s(s) in the list.";

    @Override
    public abstract void execute(Ui ui, Access access, Storage storage) throws IOException;

    protected String prepareResult(String type, String content, int count) {
        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_SUCCESS, type));
        result.append(content).append("\n");
        result.append(String.format(MESSAGE_COUNT, count, type));
        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
