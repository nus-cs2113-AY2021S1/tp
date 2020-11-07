package seedu.quotesify.commands.list;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

public class ListBookCommand extends ListCommand {

    public ListBookCommand(String arguments) {
        super(arguments);
    }

    public void execute(TextUi ui, Storage storage) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        listBooks(bookList, ui);
    }

    private void listBooks(BookList bookList, TextUi ui) {
        try {
            if (information.isEmpty()) {
                listAllBooks(bookList, ui);
            } else if (information.startsWith(FLAG_AUTHOR)) {
                listBooksByAuthor(bookList, ui);
            } else if (information.startsWith(DONE_KEYWORD) || information.startsWith(UNDONE_KEYWORD)) {
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

    private void listBooksDoneOrUndone(BookList bookList, TextUi ui) throws QuotesifyException {
        if (information.trim().equals(DONE_KEYWORD)) {
            listBooksDone(bookList, ui);
        } else if (information.trim().equals(UNDONE_KEYWORD)) {
            listBooksUndone(bookList, ui);
        } else {
            throw new QuotesifyException(ERROR_LIST_UNKNOWN_COMMAND);
        }
    }

    private void listBooksDone(BookList bookList, TextUi ui) {
        BookList completedBooks = bookList.filterDone(true);
        ui.printListDoneBook(completedBooks);
    }

    private void listBooksUndone(BookList bookList, TextUi ui) {
        BookList undoneBooks = bookList.filterDone(false);
        ui.printListUndoneBook(undoneBooks);
    }

    private void listBookDetails(BookList bookList, TextUi ui) throws NumberFormatException {
        int bookIndex = Integer.parseInt(information.trim()) - 1;
        Book book = bookList.getBook(bookIndex);
        ui.printBookDetails(book);
    }

    private void listAllBooks(BookList bookList, TextUi ui) throws QuotesifyException {
        if (bookList.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_BOOKS_IN_LIST);
        }
        ui.printAllBooks(bookList);
    }

    private void listBooksByAuthor(BookList bookList, TextUi ui) throws QuotesifyException, IndexOutOfBoundsException {
        String authorName = information.substring(4);
        BookList filteredBooks = bookList.filterByAuthor(authorName);
        if (filteredBooks.isEmpty()) {
            throw new QuotesifyException(ERROR_NO_BOOKS_BY_AUTHOR);
        }
        ui.printBooksByAuthor(filteredBooks, authorName);
    }
}
