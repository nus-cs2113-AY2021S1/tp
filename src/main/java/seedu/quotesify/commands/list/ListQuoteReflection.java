package seedu.quotesify.commands.list;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the list quote reflection command.
 */
public class ListQuoteReflection extends ListCommand {

    /**
     * Constructor for the list quote reflection command.
     *
     * @param arguments User input argument.
     */
    public ListQuoteReflection(String arguments) {
        super(arguments);
    }

    /**
     * Executes the list quote reflection command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        listQuoteReflection(quoteList, ui);
    }

    /**
     * List reflection for the user specified quote.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void listQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNumber = getQuoteNumber(information);

            if (isValidQuoteNumber(quoteNumber, quoteList)) {
                Quote quote = quoteList.getQuote(quoteNumber);
                ui.printQuoteAndReflection(quote);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Gets quote number from the user input.
     *
     * @param userInput User input argument.
     * @return Quote number of the reflection to be listed.
     * @throws QuotesifyException If missing quote number or non-integer.
     */
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

    /**
     * Checks if the quote number is valid and has a reflection.
     *
     * @param quoteNumber Quote to be checked
     * @param quoteList List of quotes.
     * @return True if valid quote number and quote has a reflection.
     * @throws QuotesifyException If invalid quote number or quote has no reflection.
     */
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
