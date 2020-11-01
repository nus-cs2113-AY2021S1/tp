package seedu.quotesify.commands.add;

import seedu.quotesify.commands.Command;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Logger;
import java.util.logging.Level;

public class AddCommand extends Command {
    public static Logger quotesifyLogger = Logger.getLogger("QuotesifyLogger");

    public String type;
    public String information;
    private String arguments;

    public AddCommand(String arguments) {
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
        case TAG_BOOK:
            quotesifyLogger.log(Level.INFO, "going to add book to booklist");
            new AddBookCommand(arguments).execute(ui, storage);
            quotesifyLogger.log(Level.INFO, "added book to booklist");
            break;
        case TAG_QUOTE:
            quotesifyLogger.log(Level.INFO, "going to add quote to quote list");
            new AddQuoteCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE_REFLECTION:
            quotesifyLogger.log(Level.INFO, "going to add reflection to quote list");
            new AddQuoteReflectionCommand(arguments).execute(ui, storage);
            break;
        case TAG_CATEGORY:
            quotesifyLogger.log(Level.INFO, "going to add category to book/quote");
            new AddCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_RATING:
            quotesifyLogger.log(Level.INFO, "going to add rating to book");
            new AddRatingCommand(arguments).execute(ui, storage);
            break;
        case TAG_TODO:
            quotesifyLogger.log(Level.INFO, "going to add task to ToDoList");
            new AddToDoCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfAddCommands();
            break;
        }
        storage.save();
    }

    public boolean isExit() {
        return false;
    }
}
