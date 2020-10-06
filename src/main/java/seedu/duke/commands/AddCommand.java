package seedu.duke.commands;

import seedu.duke.author.Author;
import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.lists.ListManager;
import seedu.duke.lists.QuotesifyList;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;
import seedu.duke.ui.TextUi;

public class AddCommand extends Command {
    private static final String TAG_BOOK = "-b";
    private static final String TAG_QUOTE = "-q";

    private String type;
    private String information;

    public AddCommand(String arguments) {
        String[] details = arguments.split(" ", 2);
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_BOOK:
            BookList books = (BookList) listManager.getList(ListManager.BOOK_LIST);
            addBook(books);
            break;
        case TAG_QUOTE:
            QuoteList quotes = (QuoteList) listManager.getList(ListManager.QUOTE_LIST);
            addQuote(quotes);
            ui.printAllQuotes(quotes);
            break;
        default:
        }
        ui.printSuccessfulAddCommand();
    }

    private void addBook(BookList books) {
        String[] titleAndAuthor = information.split("/by", 2);
        Author author = new Author(titleAndAuthor[1].trim());
        books.add(new Book(author, titleAndAuthor[0].trim()));
    }

    private void addQuote(QuoteList quotes) {
        if (information.contains("/from") && information.contains("/by")) {
            String[] quoteAndInformation = information.split("/from", 2);
            String[] referenceAndAuthor = quoteAndInformation[1].split("/by", 2);
            Author author = new Author(referenceAndAuthor[1].trim());
            quotes.add(new Quote(quoteAndInformation[0].trim(), referenceAndAuthor[0].trim(), author));
        } else if (information.contains("/from")) {
            String[] quoteAndReference = information.split("/from", 2);
            quotes.add(new Quote(quoteAndReference[0].trim(), quoteAndReference[1].trim()));
        } else if (information.contains("/by")) {
            String[] quoteAndAuthor = information.split("/by", 2);
            quotes.add(new Quote(quoteAndAuthor[0].trim(), quoteAndAuthor[1].trim()));
        } else {
            quotes.add(new Quote(information.trim()));
        }

    }

    public boolean isExit() {
        return false;
    }
}
