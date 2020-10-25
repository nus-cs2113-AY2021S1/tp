package seedu.quotesify.commands.find;

import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class FindBookCommand extends FindCommand {

    public FindBookCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        findBooks(books, ui);
    }

    private void findBooks(BookList books, TextUi ui) {
        try {
            String keyword = information.trim();
            if (keyword.isEmpty()) {
                throw new QuotesifyException(ERROR_MISSING_KEYWORD);
            }

            BookList filteredBooks = books.findByKeyword(keyword);

            if (filteredBooks.isEmpty()) {
                throw new QuotesifyException(ERROR_NO_BOOKS_IN_LIST);
            }

            ui.printBooksByKeyword(filteredBooks, keyword);
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
