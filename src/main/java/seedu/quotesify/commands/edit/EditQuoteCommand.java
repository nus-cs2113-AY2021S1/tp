package seedu.quotesify.commands.edit;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the edit quote command.
 */
public class EditQuoteCommand extends EditCommand {

    /**
     * Constructor for the edit quote command.
     *
     * @param arguments User input argument.
     */
    public EditQuoteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the edit quote command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        editQuote(quoteList, ui);
    }

    /**
     * Edits an existing quote from the quote list.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void editQuote(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNumToEdit = QuoteParser.getQuoteNumber(information, quoteList.getSize(), FLAG_EDIT);
            Quote oldQuote = quoteList.getQuote(quoteNumToEdit);
            assert oldQuote.getQuote() != null : "quote should exist in the quote list";

            Quote editedQuote = QuoteParser.getEditedQuote(information);
            assert !editedQuote.getQuote().isEmpty() : "updated quote should not be empty";

            quoteList.updateQuote(editedQuote, quoteNumToEdit);
            ui.printEditQuote(oldQuote, editedQuote);
            quotesifyLogger.log(Level.INFO, "quote has been edited successfully");
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }
}
