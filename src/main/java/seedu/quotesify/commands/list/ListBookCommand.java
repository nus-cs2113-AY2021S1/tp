package seedu.quotesify.commands.list;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

/**
 * Represents the command to list books.
 */
public class ListBookCommand extends ListCommand {

    /**
     * Constructor for the ListBook Command.
     *
     * @param arguments Input by the user.
     */
    public ListBookCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the ListBook Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        listBooks(bookList, ui);
    }

    /**
     * Parses the type of books to be listed.
     *
     * @param bookList Booklist in Quotesify.
     * @param ui Ui of the program.
     */
    private void listBooks(BookList bookList, TextUi ui) {
        try {
            if (information.isEmpty()) {
                listAllBooks(bookList, ui);
            } else if (information.contains(FLAG_AUTHOR)) {
                listBooksByAuthor(bookList, ui);
            } else if (information.contains(DONE_KEYWORD)) {
                listBooksDoneOrUndone(bookList, ui);
            } else {
                listBookDetails(bookList, ui);
            }
        } catch (IndexOutOfBoundsException e) {
            if (information.contains(FLAG_AUTHOR)) {
                ui.printErrorMessage(ERROR_NO_AUTHOR_NAME);
            } else {
                ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_LIST_UNKNOWN_COMMAND);
        }
    }

    /**
     * Lists books by completion.
     *
     * @param bookList Booklist in Quotesify.
     * @param ui Ui of the program.
     */
    private void listBooksDoneOrUndone(BookList bookList, TextUi ui) {
        if (information.trim().equals(DONE_KEYWORD)) {
            listBooksDone(bookList, ui);
        } else if (information.trim().equals("undone")) {
            listBooksUndone(bookList, ui);
        }
    }

    /**
     * Lists books which are marked as done.
     *
     * @param bookList Booklist in Quotesify.
     * @param ui Ui of the program.
     */
    private void listBooksDone(BookList bookList, TextUi ui) {
        BookList completedBooks = bookList.filterDone(true);
        ui.printListDoneBook(completedBooks);
    }

    /**
     * Lists books which are not done.
     *
     * @param bookList Booklist in Quotesify.
     * @param ui Ui of the program.
     */
    private void listBooksUndone(BookList bookList, TextUi ui) {
        BookList undoneBooks = bookList.filterDone(false);
        ui.printListUndoneBook(undoneBooks);
    }

    /**
     * Lists book details of a book.
     *
     * @param bookList Booklist in Quotesify
     * @param ui Ui of the program.
     * @throws NumberFormatException If information is not an integer.
     */
    private void listBookDetails(BookList bookList, TextUi ui) throws NumberFormatException {
        int bookIndex = Integer.parseInt(information.trim()) - 1;
        Book book = bookList.getBook(bookIndex);
        ui.printBookDetails(book);
    }

    /**
     * Lists all existing books in Quotesify.
     *
     * @param bookList Booklist in Quotesify.
     * @param ui Ui of the program.
     * @throws QuotesifyException If bookList is empty.
     */
    private void listAllBooks(BookList bookList, TextUi ui) throws QuotesifyException {
        if (bookList.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_BOOKS_IN_LIST);
        }
        ui.printAllBooks(bookList);
    }

    /**
     * Lists all existing books by an author.
     *
     * @param bookList Booklist in Quotesify.
     * @param ui Ui of the program.
     * @throws QuotesifyException If list of books by that author is empty.
     * @throws IndexOutOfBoundsException If no author name is specified.
     */
    private void listBooksByAuthor(BookList bookList, TextUi ui) throws QuotesifyException, IndexOutOfBoundsException {
        String authorName = information.substring(4);
        BookList filteredBooks = bookList.filterByAuthor(authorName);
        if (filteredBooks.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_BOOKS_BY_AUTHOR);
        }
        ui.printBooksByAuthor(filteredBooks, authorName);
    }
}
