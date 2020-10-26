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
            if (!information.contains(FLAG_EDIT)) {
                throw new QuotesifyException(ERROR_MISSING_EDIT_FLAG);
            }

            int quoteNumToEdit = QuoteParser.getQuoteNumber(information, quoteList, Command.FLAG_EDIT);
            String editedReflection = QuoteParser.getEditedReflection(information);

            if (!editedReflection.isEmpty()) {
                quoteList.updateReflection(editedReflection, quoteNumToEdit);
                ui.printEditQuoteReflection(quoteList.getQuote(quoteNumToEdit), editedReflection);
            } else {
                throw new QuotesifyException(ERROR_MISSING_REFLECTION);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

}
