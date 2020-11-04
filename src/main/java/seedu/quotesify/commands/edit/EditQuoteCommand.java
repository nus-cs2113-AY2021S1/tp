package seedu.quotesify.commands.edit;

import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class EditQuoteCommand extends EditCommand {

    public EditQuoteCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        editQuote(quoteList, ui);
    }

    private void editQuote(QuoteList quoteList, TextUi ui) {
        try {
                int quoteNumToEdit = QuoteParser.getQuoteNumber(information, quoteList, Command.FLAG_EDIT);
                Quote oldQuote = quoteList.getQuote(quoteNumToEdit);
                Quote editedQuote = QuoteParser.getEditedQuote(information);
                quoteList.updateQuote(editedQuote, quoteNumToEdit);
                ui.printEditQuote(oldQuote, editedQuote);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
