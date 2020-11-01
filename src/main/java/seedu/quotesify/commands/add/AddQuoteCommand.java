package seedu.quotesify.commands.add;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

public class AddQuoteCommand extends AddCommand {

    public AddQuoteCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        addQuote(quoteList, ui);
    }

    private void addQuote(QuoteList quoteList, TextUi ui) {
        try {
            Quote quote = QuoteParser.parseAddParameters(information);
            boolean isDuplicate = quoteList.checkDuplicateQuote(quote);
            if (isDuplicate) {
                throw new QuotesifyException(ERROR_DUPLICATE_QUOTE);
            }
            quoteList.add(quote);
            ui.printAddQuote(quote);
            quotesifyLogger.log(Level.INFO, "add quote to quote list success");
        } catch (QuotesifyException e) {
            System.out.println(e.getMessage());
            quotesifyLogger.log(Level.INFO, "add quote to quote list failed");
            quotesifyLogger.log(Level.WARNING, e.getMessage());
        }
    }
}
