package seedu.quotesify.commands.delete;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the delete quote command.
 */
public class DeleteQuoteCommand extends DeleteCommand {

    /**
     * Constructor for the delete quote command.
     *
     * @param arguments User input argument.
     */
    public DeleteQuoteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the delete quote command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        deleteQuote(quoteList, ui);
        storage.save();
    }

    /**
     * Deletes a quote from list of quotes.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void deleteQuote(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNumber = getQuoteNumberToDelete(information);
            Quote quoteToBeDeleted = quoteList.getQuote(quoteNumber);
            quoteList.delete(quoteNumber);
            ui.printDeleteQuote(quoteToBeDeleted.getQuote());
            quotesifyLogger.log(Level.INFO, "quote deleted successfully");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
            quotesifyLogger.log(Level.WARNING, ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Returns quote number of the quote to be deleted.
     *
     * @param userInput User input argument.
     * @return Quote number to be deleted.
     * @throws QuotesifyException If quote number field is empty
     */
    private int getQuoteNumberToDelete(String userInput) throws QuotesifyException {
        if (userInput.trim().isEmpty()) {
            throw new QuotesifyException(ERROR_NO_QUOTE_NUMBER);
        }
        return Integer.parseInt(userInput.trim()) - 1;
    }

}
