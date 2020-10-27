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
                listAllQuotes(quoteList, ui);
            } else if (information.contains(Command.FLAG_AUTHOR) && information.contains(Command.FLAG_REFERENCE)) {
                information = information.substring(1);
                HashMap<String, String> referenceAndAuthorName = QuoteParser.parseReferenceAndAuthor(information);
                String reference = referenceAndAuthorName.get(Command.REFERENCE_KEYWORD).toLowerCase();
                String authorName = referenceAndAuthorName.get(Command.AUTHORNAME_KEYWORD).toLowerCase();
                listQuotesByReferenceAndAuthor(quoteList, reference, authorName, ui);
            } else if (information.contains(Command.FLAG_AUTHOR)) {
                String authorName = QuoteParser.parseListWithAuthor(information).toLowerCase();
                listQuotesByAuthor(quoteList, authorName, ui);
            } else if (information.contains(Command.FLAG_REFERENCE)) {
                String reference = QuoteParser.parseListWithReference(information).toLowerCase();
                listQuotesByReference(quoteList, reference, ui);
            } else {
                throw new QuotesifyException(ERROR_LIST_UNKNOWN_COMMAND);
            }
        } catch (QuotesifyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listQuotesByReferenceAndAuthor(QuoteList quoteList, String reference, String authorName, TextUi ui) {
        ui.printAllQuotesByReferenceAndAuthor(quoteList, reference, authorName);
    }

    private void listAllQuotes(QuoteList quoteList, TextUi ui) {
        ui.printAllQuotes(quoteList);
    }

    private void listQuotesByAuthor(QuoteList quoteList, String authorName, TextUi ui) {
        ui.printAllQuotesByAuthor(quoteList, authorName);
    }

    private void listQuotesByReference(QuoteList quoteList, String reference, TextUi ui) {
        ui.printAllQuotesByReference(quoteList, reference);
    }
}
