package seedu.quotesify.commands.find;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class FindQuoteCommand extends FindCommand {

    public FindQuoteCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        findQuote(quoteList, ui);
        storage.save();
    }

    private void findQuote(QuoteList quoteList, TextUi ui) {
        try {
            String keyword = information.trim().toLowerCase();
            if (!keyword.isEmpty()) {
                String resultList = quoteList.findQuoteByKeyword(quoteList, keyword);
                if (!resultList.isEmpty()) {
                    ui.printFindQuoteSuccess(resultList);
                } else {
                    ui.printFindQuoteFail();
                }
            } else {
                throw new QuotesifyException(ERROR_FIND_KEYWORD_MISSING);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(ERROR_FIND_KEYWORD_MISSING);
        }
    }
}
