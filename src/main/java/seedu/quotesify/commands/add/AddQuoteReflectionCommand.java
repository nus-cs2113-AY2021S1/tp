package seedu.quotesify.commands.add;

import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the add quote reflection command.
 */
public class AddQuoteReflectionCommand extends AddCommand {

    /**
     * Constructor for the add quote reflection command.
     *
     * @param arguments User input argument.
     */
    public AddQuoteReflectionCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the add quote reflection command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        addQuoteReflection(quoteList, ui);
    }

    /**
     * Adds the reflection to the specified quote.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void addQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNum = QuoteParser.getQuoteNumber(information, quoteList.getSize(), FLAG_REFLECT);
            String reflection = QuoteParser.getReflectionToAdd(information);
            assert !reflection.isEmpty() : "reflection cannot be empty";

            quoteList.addReflection(reflection, quoteNum);
            ui.printAddReflection(quoteList.getQuote(quoteNum));
            quotesifyLogger.log(Level.INFO, "add reflection to quote success");
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }
}
