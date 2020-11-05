package seedu.quotesify.commands.edit;

import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class EditQuoteReflectionCommand extends  EditCommand {

    public EditQuoteReflectionCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        editQuoteReflection(quoteList, ui);
    }

    private void editQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNumToEdit = QuoteParser.getQuoteNumber(information, quoteList.getSize(), FLAG_EDIT);
            if (!hasReflection(quoteNumToEdit, quoteList)) {
                throw new QuotesifyException(ERROR_NO_REFLECTION);
            }
            String editedReflection = QuoteParser.getEditedReflection(information);
            quoteList.updateReflection(editedReflection, quoteNumToEdit);
            ui.printEditQuoteReflection(quoteList.getQuote(quoteNumToEdit));
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private boolean hasReflection(int quoteNumber, QuoteList quoteList) {
        if (quoteList.getQuote(quoteNumber).getReflection() ==  null) {
            return false;
        }
        return true;
    }

}
