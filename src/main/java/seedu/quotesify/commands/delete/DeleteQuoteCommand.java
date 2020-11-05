package seedu.quotesify.commands.delete;

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
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        deleteQuote(quoteList, ui, information);
        storage.save();
    }

    private void deleteQuote(QuoteList quoteList, TextUi ui, String information) {
        try {
            if (information.trim().isEmpty()) {
                throw new QuotesifyException(ERROR_NO_QUOTE_NUMBER);
            }
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            Quote quoteToBeDeleted = quoteList.getQuote(quoteNumber);
            quoteList.delete(quoteNumber);
            ui.printDeleteQuote(quoteToBeDeleted.getQuote());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

}
