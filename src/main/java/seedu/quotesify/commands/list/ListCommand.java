package seedu.quotesify.commands.list;

import seedu.quotesify.book.Book;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.quote.QuoteParser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.todo.ToDoList;
import seedu.quotesify.ui.TextUi;

import java.util.HashMap;

public class ListCommand extends Command {
    public String type;
    public String information;
    private String arguments;

    public ListCommand(String arguments) {
        this.arguments = arguments;
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        assert details.length == 2;
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_CATEGORY:
            new ListCategoryCommand(arguments).execute(ui, storage);
            break;
        case TAG_RATING:
            new ListRatingCommand(arguments).execute(ui, storage);
            break;
        case TAG_TODO:
            new ListToDoCommand(arguments).execute(ui, storage);
            break;
        case TAG_BOOKMARK:
            new ListBookmarkCommand(arguments).execute(ui, storage);
            break;
        case TAG_QUOTE:
            QuoteList quoteListList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            listQuotes(quoteListList, ui);
            break;
        case TAG_QUOTE_REFLECTION:
            QuoteList quotes = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
            listQuoteReflection(quotes, ui);
            break;
        case TAG_BOOK:
            new ListBookCommand(arguments).execute(ui, storage);
            break;
        default:
            ui.printListOfListCommands();
            break;
        }
    }

    private void listQuoteReflection(QuoteList quotes, TextUi ui) {
        try {
            int quoteNumber = Integer.parseInt(information.trim()) - 1;
            if (information.isEmpty()) {
                throw new QuotesifyException(ERROR_NO_QUOTE_NUMBER);
            } else if (quoteNumber < 0 || quoteNumber > quotes.getSize()) {
                throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
            } else {
                Quote quote = quotes.getQuote(quoteNumber);
                ui.printQuoteAndReflection(quote);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    private void listQuotes(QuoteList quoteList, TextUi ui) {
        try {
            if ((information.isEmpty())) {
                listAllQuotes(quoteList, ui);
            } else if (information.contains(Command.FLAG_AUTHOR) && information.contains(Command.FLAG_REFERENCE)) {
                information = information.substring(1);
                HashMap<String, String> referenceAndAuthorName = QuoteParser.parseReferenceAndAuthor(information);
                String reference = referenceAndAuthorName.get(Command.REFERENCE_KEYWORD);
                String authorName = referenceAndAuthorName.get(Command.AUTHORNAME_KEYWORD);
                listQuotesByReferenceAndAuthor(quoteList, reference, authorName, ui);
            } else if (information.contains(Command.FLAG_AUTHOR)) {
                String authorName = QuoteParser.parseListWithAuthor(information);
                listQuotesByAuthor(quoteList, authorName, ui);
            } else if (information.contains(Command.FLAG_REFERENCE)) {
                String reference = QuoteParser.parseListWithReference(information);
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

    @Override
    public boolean isExit() {
        return false;
    }
}
