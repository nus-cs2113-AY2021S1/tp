package seedu.quotesify.commands.delete;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class DeleteQuoteReflectionCommand extends DeleteCommand {

    public DeleteQuoteReflectionCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        deleteQuoteReflection(quoteList, ui, information);
    }

    private void deleteQuoteReflection(QuoteList quoteList, TextUi ui, String information) {
        try {
            int quoteNumber = getQuoteNumberToDelete(information);
            if (!hasReflection(quoteNumber, quoteList)) {
                throw new QuotesifyException(ERROR_NO_REFLECTION);
            }
            quoteList.deleteReflection(quoteNumber);
            ui.printDeleteQuoteReflection(quoteList.getQuote(quoteNumber).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private int getQuoteNumberToDelete(String userInput) {
        int quoteNumber = Integer.parseInt(userInput.trim()) - 1;
        return quoteNumber;
    }

    private boolean hasReflection(int quoteNumber, QuoteList quoteList) {
        if (quoteList.getQuote(quoteNumber).getReflection() ==  null) {
            return false;
        }
        return true;
    }

}
