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
            int quoteNumber = getQuoteNumber(information);
            if (isValidQuoteNumber(quoteNumber, quoteList)) {
                Quote quote = quoteList.getQuote(quoteNumber);
                ui.printQuoteAndReflection(quote);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private int getQuoteNumber(String userInput) throws QuotesifyException {
        try {
            if (userInput.isEmpty()) {
                throw new QuotesifyException(ERROR_NO_QUOTE_NUMBER);
            }
            return Integer.parseInt(userInput.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
        }
    }

    private boolean isValidQuoteNumber(int quoteNumber, QuoteList quoteList) throws QuotesifyException {
        if (quoteList.getSize() == 0) {
            throw new QuotesifyException(LIST_NO_QUOTES_SAVED_MESSAGE);
        } else if (quoteNumber < 0 || quoteNumber >= quoteList.getSize()) {
            throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
        } else if (quoteList.getQuote(quoteNumber).getReflection()  ==  null) {
            throw new QuotesifyException(ERROR_NO_REFLECTION);
        } else {
            return true;
        }
    }


}
