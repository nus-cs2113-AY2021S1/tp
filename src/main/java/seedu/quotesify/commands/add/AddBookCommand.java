package seedu.quotesify.commands.add;

import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.commands.add.AddCommand;
import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;

public class AddBookCommand extends AddCommand {
    public AddBookCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        addBook(books, ui);
    }

    private void addBook(BookList books, TextUi ui) {
        try {
            String[] titleAndAuthor = information.split(Command.FLAG_AUTHOR, 2);
            // if user did not provide author, let titleAndAuthor[1] be empty string
            if (titleAndAuthor.length == 1) {
                titleAndAuthor = new String[]{titleAndAuthor[0], ""};
            }

            String title = titleAndAuthor[0].trim();
            String authorName = titleAndAuthor[1].trim();

            checkMissingInformation(title, authorName);
            Book newBook = createNewBook(books, title, authorName);

            books.add(newBook);
            ui.printAddBook(newBook);

        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
            quotesifyLogger.log(Level.INFO, "add book to booklist failed");
        }
    }

    private Book createNewBook(BookList books, String title, String authorName) throws QuotesifyException {
        Book newBook;
        Author existingAuthor = books.findExistingAuthor(authorName);

        if (existingAuthor == null) {
            // Book is definitely unique
            newBook = new Book(new Author(authorName), title);
        } else {
            books.ensureNoSimilarBooks(title, existingAuthor.getName());
            newBook = new Book(existingAuthor, title);
        }

        return newBook;
    }

    private void checkMissingInformation(String title, String authorName) throws QuotesifyException {
        if (title.isEmpty()) {
            throw new QuotesifyException(ERROR_BOOK_TITLE_MISSING);
        }
        if (authorName.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_AUTHOR_NAME);
        }
    }
}
