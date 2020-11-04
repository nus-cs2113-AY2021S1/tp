package seedu.quotesify.commands.list;

import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.HashMap;

public class ListQuoteCommand extends ListCommand {

    public ListQuoteCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        listQuotes(quoteList, ui);
    }

    private void listQuotes(QuoteList quoteList, TextUi ui) {
        try {
            if ((information.isEmpty())) {
                ui.printAllQuotes(quoteList);
            } else if (information.contains(FLAG_AUTHOR) && information.contains(FLAG_REFERENCE)) {
                HashMap<String, String> referenceAndAuthor =
                        QuoteParser.getReferenceAndAuthor(information.substring(1));
                String reference = referenceAndAuthor.get(Command.REFERENCE_KEYWORD);
                String authorName = referenceAndAuthor.get(Command.AUTHORNAME_KEYWORD);
                ui.printAllQuotesByReferenceAndAuthor(quoteList, reference, authorName);
            } else if (information.contains(FLAG_AUTHOR)) {
                String authorName = QuoteParser.parseListWithAuthor(information);
                ui.printAllQuotesByAuthor(quoteList, authorName);
            } else if (information.contains(FLAG_REFERENCE)) {
                String reference = QuoteParser.parseListWithReference(information);
                ui.printAllQuotesByReference(quoteList, reference);
            } else {
                throw new QuotesifyException(ERROR_LIST_UNKNOWN_COMMAND);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
