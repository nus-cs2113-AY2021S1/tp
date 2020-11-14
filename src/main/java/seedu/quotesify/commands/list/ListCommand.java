package seedu.quotesify.commands.list;

import seedu.quotesify.commands.Command;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the List Command.
 */
public class ListCommand extends Command {
    public static Logger quotesifyLogger = Logger.getLogger("QuotesifyLogger");

    public String type;
    public String information;
    private String arguments;

    /**
     * Constructor for the List command.
     *
     * @param arguments Input by the user.
     */
    public ListCommand(String arguments) {
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
     * Executes the List command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_CATEGORY:
            new ListCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_RATING:
            quotesifyLogger.log(Level.INFO, "going to list rating of books");
            new ListRatingCommand(arguments).execute(ui, storage);
            break;
        case TAG_TODO:
            new ListToDoCommand(arguments).execute(ui, storage);
            break;
        //@@author lunzard
        case TAG_BOOKMARK:
            new ListBookmarkCommand(arguments).execute(ui, storage);
            break;
        //@@author
        case TAG_QUOTE:
            new ListQuoteCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE_REFLECTION:
            new ListQuoteReflection(arguments).execute(ui, storage);
            break;
        case TAG_BOOK:
            new ListBookCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfListCommands();
            break;
        }
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
