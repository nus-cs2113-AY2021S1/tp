package seedu.quotesify.commands.delete;

import seedu.quotesify.commands.Command;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the Delete Command.
 */
public class DeleteCommand extends Command {
    public static Logger quotesifyLogger = Logger.getLogger("QuotesifyLogger");

    public String type;
    public String information;
    private String arguments;

    /**
     * Constructor for the Delete command.
     *
     * @param arguments Input by the user.
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
        String[] details = arguments.split(" ", 2);

        /** if user did not provide arguments, let details[1] be empty string */
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    /**
     * Executes the Delete command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_CATEGORY:
            new DeleteCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOK:
            new DeleteBookCommand(arguments).execute(ui, storage);
            break;
        case TAG_RATING:
            quotesifyLogger.log(Level.INFO, "going to delete rating from book");
            new DeleteRatingCommand(arguments).execute(ui, storage);
            break;
        //@@author lunard
        case TAG_TODO:
            new DeleteToDoCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOKMARK:
            new DeleteBookmarkCommand(arguments).execute(ui, storage);
            break;
        //@@author
        case TAG_QUOTE:
            new DeleteQuoteCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE_REFLECTION:
            new DeleteQuoteReflectionCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfDeleteCommands();
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
