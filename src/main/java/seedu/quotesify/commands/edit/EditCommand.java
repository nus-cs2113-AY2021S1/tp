package seedu.quotesify.commands.edit;

import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class EditCommand extends Command {

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
            new EditRatingCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOK:
            new EditBookCommand(arguments).execute(ui, storage);
            break;
        case TAG_CATEGORY:
            new EditCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            editQuote(quotes, ui);
            break;
        case TAG_QUOTE_REFLECTION:
            QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            editQuoteReflection(quoteList, ui);
            break;
        default:
            ui.printListOfEditCommands();
            break;
        }
        storage.save();
    }

    private void editQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            if (information.contains(FLAG_EDIT)) {
                int quoteNumToEdit = QuoteParser.parseQuoteNumber(information, quoteList, Command.FLAG_EDIT);
                String editedReflection = QuoteParser.getEditedReflection(information);
                if (!editedReflection.isEmpty()) {
                    quoteList.updateReflection(editedReflection, quoteNumToEdit);
                    ui.printEditQuoteReflection(quoteList.getQuote(quoteNumToEdit), editedReflection);
                } else {
                    throw new QuotesifyException(ERROR_MISSING_REFLECTION);
                }
            } else {
                throw new QuotesifyException(ERROR_MISSING_EDIT_FLAG);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void editQuote(QuoteList quotes, TextUi ui) {
        try {
            if (information.contains(FLAG_EDIT)) {
                int quoteNumToEdit = QuoteParser.parseQuoteNumber(information, quotes, Command.FLAG_EDIT);
                Quote oldQuote = quotes.getQuote(quoteNumToEdit);
                Quote editedQuote = QuoteParser.getEditedQuote(information);
                quotes.updateQuote(editedQuote, quoteNumToEdit);
                ui.printEditQuote(oldQuote, editedQuote);
            } else {
                throw new QuotesifyException(ERROR_MISSING_EDIT_FLAG);
            }

        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
