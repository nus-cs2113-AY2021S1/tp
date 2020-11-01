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
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            if (quoteList.getQuote(quoteNumber).getReflection() != null) {
                quoteList.deleteReflection(quoteNumber);
                ui.printDeleteQuoteReflection(quoteList.getQuote(quoteNumber).toString());
            } else {
                throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | QuotesifyException e) {
            System.out.println(ERROR_INVALID_QUOTE_NUM);
        }
    }

}
