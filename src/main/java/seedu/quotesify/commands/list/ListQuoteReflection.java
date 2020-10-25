package seedu.quotesify.commands.list;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class ListQuoteReflection extends ListCommand {

    public ListQuoteReflection(String arguements) {
        super(arguements);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        listQuoteReflection(quoteList, ui);
    }

    private void listQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            if (information.trim().isEmpty()) {
                throw new QuotesifyException(ERROR_NO_QUOTE_NUMBER);
            }
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            if (quoteNumber < 0 || quoteNumber > quoteList.getSize()) {
                throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
            } else {
                Quote quote = quoteList.getQuote(quoteNumber);
                ui.printQuoteAndReflection(quote);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
        }
    }
}
