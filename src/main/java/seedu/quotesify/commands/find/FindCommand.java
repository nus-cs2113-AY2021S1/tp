package seedu.quotesify.commands.find;

import seedu.quotesify.commands.Command;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the Find Command.
 */
public class FindCommand extends Command {
    public static Logger quotesifyLogger = Logger.getLogger("QuotesifyLogger");

    public String type;
    public String information;
    private String arguments;

    /**
     * Constructor for the Find command.
     *
     * @param arguments Input by the user.
     */
    public FindCommand(String arguments) {
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
     * Executes the Find command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_RATING:
            quotesifyLogger.log(Level.INFO, "going to find rating of books");
            new FindRatingCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOK:
            new FindBookCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE:
            new FindQuoteCommand(arguments).execute(ui, storage);
            break;
        case TAG_CATEGORY:
            new FindCategoryCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfFindCommands();
            break;
        }
        storage.save();
    }

    /**
     * Decides if the program should be terminated.
     *
     * @return decision to terminate the program.
     */
    public boolean isExit() {
        return false;
    }
}
