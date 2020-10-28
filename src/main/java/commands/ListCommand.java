package commands;

import access.Access;
import exception.EmptyFileException;
import storage.Storage;
import ui.Ui;


public abstract class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of modules / chapters / flashcards available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    public static final String MESSAGE_EXIST = "Here are the %s(s) in your list:";
    public static final String MESSAGE_DOES_NOT_EXIST = "There are no %s(s) in your list.";

    @Override
    public abstract void execute(Ui ui, Access access, Storage storage) throws EmptyFileException;

    @Override
    public boolean isExit() {
        return false;
    }
}
