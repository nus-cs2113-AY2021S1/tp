package seedu.quotesify.commands.find;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 *  Represents the find quote command.
 */
public class FindQuoteCommand extends FindCommand {

    /**
     * Constructor for the find quote command.
     *
     * @param arguments User input argument.
     */
    public FindQuoteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the find quote command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        findQuote(quoteList, ui);
        storage.save();
    }

    /**
     * Finds quotes that contains the specified keyword
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void findQuote(QuoteList quoteList, TextUi ui) {
        try {
            String keyword = information.trim().toLowerCase();
            if (keyword.isEmpty()) {
                throw new QuotesifyException(ERROR_FIND_KEYWORD_MISSING);
            }
            String findResultList = quoteList.findQuoteByKeyword(quoteList, keyword);

            if (findResultList.isEmpty()) {
                ui.printFindQuoteFail();
                quotesifyLogger.log(Level.INFO, "no quotes with matching keyword!");
            }
            ui.printFindQuoteSuccess(findResultList);
            quotesifyLogger.log(Level.INFO, "quotes with matching keyword found!");
        } catch (QuotesifyException e) {
            ui.printErrorMessage(ERROR_FIND_KEYWORD_MISSING);
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }
}
