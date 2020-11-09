package seedu.quotesify.commands.done;

import seedu.quotesify.commands.Command;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

/**
 * Represents the command to mark books or tasks as complete.
 */
public class DoneCommand extends Command {
    public String type;
    public String information;
    private String arguments;

    /**
     * Constructor for the Done Command.
     *
     * @param arguments Inputs by the user.
     */
    public DoneCommand(String arguments) {
        this.arguments = arguments;
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    /**
     * Executes the Done Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        //@@author lunzard
        case TAG_TODO:
            new DoneToDoCommand(arguments).execute(ui, storage);
            break;
        //@@author
        case TAG_BOOK:
            new DoneBookCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printDoneCommandUsage();
            break;
        }
        storage.save();
    }

    /**
     * Decides if the program should be terminated.
     *
     * @return decision to terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
