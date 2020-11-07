package seedu.quotesify.commands.done;

import seedu.quotesify.commands.Command;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class DoneCommand extends Command {
    public String type;
    public String information;
    private String arguments;

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

    @Override
    public boolean isExit() {
        return false;
    }
}
