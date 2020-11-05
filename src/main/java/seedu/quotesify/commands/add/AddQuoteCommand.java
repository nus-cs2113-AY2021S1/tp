package seedu.quotesify.commands.add;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the add quote command.
 */
public class AddQuoteCommand extends AddCommand {

    /**
     * Constructor for the add quote command.
     *
     * @param arguments User input argument.
     */
    public AddQuoteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the add quote command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        addQuote(quoteList, ui);
    }

    /**
     * Adds a quote to the list of quotes.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void addQuote(QuoteList quoteList, TextUi ui) {
        try {
            Quote quote = QuoteParser.parseAddParameters(information);
            assert !quote.getQuote().isEmpty() : "quote should not be empty";

            boolean isDuplicate = quoteList.checkDuplicateQuote(quote);
            if (isDuplicate) {
                throw new QuotesifyException(ERROR_DUPLICATE_QUOTE);
            }

            quoteList.add(quote);
            ui.printAddQuote(quote);
            quotesifyLogger.log(Level.INFO, "add quote to quote list success");
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }
}
