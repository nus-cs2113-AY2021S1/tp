package seedu.quotesify.commands.done;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class DoneBookCommand extends DoneCommand {

    public DoneBookCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        doneBooks(books, ui);
        storage.save();
    }

    private void doneBooks(BookList books, TextUi ui) {
        try {
            int bookIndex = Integer.parseInt(information.trim()) - 1;
            Book book = books.getBook(bookIndex);
            book.setDone(true);
            ui.printDoneBook(book);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
        }
    }
}
