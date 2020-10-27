package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public abstract class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes %1$s based on the index in the list. \n"
            + "Parameters: %2$s_INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_SUCCESS = "Got it. I've removed this %1$s:\n";
    public static final String MESSAGE_COUNT = "Now you have %1$d %2$s(s) in the list.";

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
    public abstract boolean isExit();
}
