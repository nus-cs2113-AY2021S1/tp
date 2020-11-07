package seedu.quotesify.commands.delete;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

/**
 * Represents the command to delete books from Quotesify's booklist.
 */
public class DeleteBookCommand extends DeleteCommand {

    /**
     * Constructor for DeleteBookCommand.
     *
     * @param arguments Input by the user.
     */
    public DeleteBookCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DeleteBook Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public void execute(TextUi ui, Storage storage) {
        BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        deleteBook(books, ui);
    }

    /**
     * Deletes the book from Quotesify's booklist.
     *
     * @param books Booklist in Quotesify.
     * @param ui Ui of the program.
     */
    private void deleteBook(BookList books, TextUi ui) {
        try {
            int bookIndex = Integer.parseInt(information.trim()) - 1;
            Book book = books.getBook(bookIndex);
            String bookTitle = book.getTitle();
            String author = book.getAuthor().getName();

            // clear bookmarks before deleting the entire book.
            BookmarkList bookmarks = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
            clearBookmark(books, bookmarks, bookTitle, ui);

            // delete ratings before deleting the entire book.
            RatingList ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
            for (Rating rating : ratings.getList()) {
                if (rating.getTitle().equals(bookTitle) && rating.getAuthor().equals(author)) {
                    ratings.delete(ratings.getList().indexOf(rating));
                    break;
                }
            }

            books.delete(bookIndex);
            ui.printDeleteBook(book);

        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage(ERROR_NO_BOOK_FOUND);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(ERROR_INVALID_BOOK_NUM);
        }
    }

    /**
     * Clears bookmarks from the specified book.
     * Finds the book by its title.
     *
     * @param books Booklist in Quotesify.
     * @param bookmarks Bookmark list in Quotesify.
     * @param titleName Title of the book.
     * @param ui Ui of the program.
     */
    private void clearBookmark(BookList books, BookmarkList bookmarks, String titleName, TextUi ui) {
        Book targetBook = books.findByTitle(titleName);
        if (targetBook != null) {
            clearBookmarkFromDeletedBook(targetBook, bookmarks, ui);
        }
    }

    /**
     * Clears bookmarks from found book.
     *
     * @param targetBook Book which bookmarks are to be cleared.
     * @param bookmarks Bookmark list in Quotesify.
     * @param ui Ui of the program.
     */
    private void clearBookmarkFromDeletedBook(Book targetBook, BookmarkList bookmarks, TextUi ui) {
        Bookmark bookmarkToBeDeleted = bookmarks.find(targetBook);

        if (bookmarkToBeDeleted != null) {
            bookmarks.delete(bookmarkToBeDeleted);
        }
    }
}
