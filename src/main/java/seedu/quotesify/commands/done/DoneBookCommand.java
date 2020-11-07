package seedu.quotesify.commands.done;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

/**
 * Represents the command to mark books as done.
 */
public class DoneBookCommand extends DoneCommand {

    /**
     * Constructor for the DoneBook Command.
     *
     * @param arguments Input by the user.
     */
    public DoneBookCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DoneBook Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        doneBooks(books, ui);
    }

    /**
     * Marks the book which corresponds to the book index as done.
     *
     * @param books Booklist in Quotesify.
     * @param ui Ui of the program.
     */
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
