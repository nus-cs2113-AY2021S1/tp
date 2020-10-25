package seedu.quotesify.commands.delete;

import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class DeleteQuoteCommand extends DeleteCommand {

    public DeleteQuoteCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        deleteQuote(quotes, ui, information);
        storage.save();
    }

    private void deleteQuote(QuoteList quotes, TextUi ui, String information) {
        try {
            if (information.trim().isEmpty()) {
                throw new QuotesifyException(ERROR_NO_QUOTE_NUMBER);
            }
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            Quote quoteToBeDeleted = quotes.getQuote(quoteNumber);
            quotes.delete(quoteNumber);
            ui.printDeleteQuote(quoteToBeDeleted.getQuote());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

}
