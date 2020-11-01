package seedu.quotesify.commands.edit;

import seedu.quotesify.commands.Command;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditCommand extends Command {
    public static Logger quotesifyLogger = Logger.getLogger("QuotesifyLogger");

    public String type;
    public String information;
    private String arguments;

    public EditCommand(String arguments) {
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
        case TAG_RATING:
            quotesifyLogger.log(Level.INFO, "going to edit rating of book");
            new EditRatingCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOK:
            new EditBookCommand(arguments).execute(ui, storage);
            break;
        case TAG_CATEGORY:
            new EditCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE:
            new EditQuoteCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE_REFLECTION:
            new EditQuoteReflectionCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfEditCommands();
            break;
        }
        storage.save();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
