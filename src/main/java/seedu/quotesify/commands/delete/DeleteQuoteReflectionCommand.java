package seedu.quotesify.commands.delete;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the delete quote reflection command.
 */
public class DeleteQuoteReflectionCommand extends DeleteCommand {

    /**
     * Constructor for the delete quote reflection command.
     *
     * @param arguments User input argument.
     */
    public DeleteQuoteReflectionCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the delete quote reflection command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        deleteQuoteReflection(quoteList, ui);
    }

    /**
     * Deletes the reflection of a quote.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void deleteQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNumber = getQuoteNumberToDelete(information);
            if (!hasReflection(quoteNumber, quoteList)) {
                throw new QuotesifyException(ERROR_NO_REFLECTION);
            }
            assert quoteList.getQuote(quoteNumber).getReflection() != null : "Quote should have a reflection";
            quoteList.deleteReflection(quoteNumber);
            ui.printDeleteQuoteReflection(quoteList.getQuote(quoteNumber).toString());
            quotesifyLogger.log(Level.INFO, "quote reflection deleted successfully");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_INVALID_QUOTE_NUM);
            quotesifyLogger.log(Level.WARNING, ERROR_INVALID_QUOTE_NUM);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Returns quote number of the reflection to be deleted.
     *
     * @param userInput User input argument.
     * @return Quote number of the reflection to be deleted.
     * @throws QuotesifyException If quote number field is empty
     */
    private int getQuoteNumberToDelete(String userInput) throws QuotesifyException {
        if (userInput.trim().isEmpty()) {
            throw new QuotesifyException(ERROR_NO_QUOTE_NUMBER);
        }
        return Integer.parseInt(userInput.trim()) - 1;
    }

    /**
     * Checks if quote number has a reflection tagged to it.
     *
     * @param quoteNumber Quote number to check for.
     * @param quoteList List of quotes.
     * @return True if quote has a reflection and false otherwise.
     */
    private boolean hasReflection(int quoteNumber, QuoteList quoteList) {
        if (quoteList.getQuote(quoteNumber).getReflection() ==  null) {
            return false;
        }
        return true;
    }

}
