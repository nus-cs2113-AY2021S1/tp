package seedu.quotesify.commands.edit;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

/**
 * Represents the edit quote reflection command.
 */
public class EditQuoteReflectionCommand extends  EditCommand {

    /**
     * Constructor for the edit quote reflection command.
     *
     * @param arguments User input argument.
     */
    public EditQuoteReflectionCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the edit quote reflection command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        editQuoteReflection(quoteList, ui);
    }

    /**
     * Edits an existing quote reflection from the quote list.
     *
     * @param quoteList List of quotes.
     * @param ui Ui of the program.
     */
    private void editQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNumToEdit = QuoteParser.getQuoteNumber(information, quoteList.getSize(), FLAG_EDIT);

            if (!hasReflection(quoteNumToEdit, quoteList)) {
                throw new QuotesifyException(ERROR_NO_REFLECTION);
            }

            String editedReflection = QuoteParser.getEditedReflection(information);
            assert !editedReflection.isEmpty() : "reflection field should not be empty";

            quoteList.updateReflection(editedReflection, quoteNumToEdit);
            ui.printEditQuoteReflection(quoteList.getQuote(quoteNumToEdit));
            quotesifyLogger.log(Level.INFO, "quote reflection has been edited successfully");
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Checks that the specified quote has an existing reflection tagged to it.
     *
     * @param quoteNumber Index of quote in the quote list.
     * @param quoteList  List of quotes.
     * @return True if reflection is not null, false otherwise.
     */
    private boolean hasReflection(int quoteNumber, QuoteList quoteList) {
        if (quoteList.getQuote(quoteNumber).getReflection() ==  null) {
            return false;
        }
        return true;
    }

}
