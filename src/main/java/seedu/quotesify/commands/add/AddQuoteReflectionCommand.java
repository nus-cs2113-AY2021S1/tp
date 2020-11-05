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

public class AddQuoteReflectionCommand extends AddCommand {

    public AddQuoteReflectionCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        addQuoteReflection(quoteList, ui);
    }

    private void addQuoteReflection(QuoteList quoteList, TextUi ui) {
        try {
            int quoteNum = QuoteParser.getQuoteNumber(information, quoteList.getSize(), FLAG_REFLECT);
            String reflection = QuoteParser.getReflectionToAdd(information);
            quoteList.addReflection(reflection, quoteNum);
            ui.printAddReflection(quoteList.getQuote(quoteNum));
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.INFO, "add reflection to quote failed");
        }
    }
}
