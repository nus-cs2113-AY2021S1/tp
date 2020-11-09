package seedu.quotesify.commands.bookmark;

import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author lunzard

/**
 * Represents the command to add or update bookmarks to Quotesify's Bookmarklist.
 */
public class BookmarkCommand extends Command {
    private String type;
    private String information;

    public static Logger addLogger = Logger.getLogger("QuotesifyLogger");

    /**
     * Constructor for the Bookmark Command.
     *
     * @param arguments Inputs by the user.
     */
    public BookmarkCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    /**
     * Executes the Bookmark Command.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    @Override
    public void execute(TextUi ui, Storage storage) {
        switch (type) {
        case TAG_BOOK:
            BookList books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
            BookmarkList bookmarks = (BookmarkList) ListManager.getList(ListManager.BOOKMARK_LIST);
            handleBookmarkByNumber(books, bookmarks, ui);
            break;

        default:
            System.out.println(ERROR_INVALID_TAG);
            break;
        }
        storage.save();
    }


    /**
     * Decide whether to add or update bookmarks from existence of the bookmark
     * in the BookmarkList
     *
     * @param books BookList in Quotesify.
     * @param bookmarks BookmarkList in Quotesify.
     * @param ui Ui of the program.
     */
    public void handleBookmarkByNumber(BookList books, BookmarkList bookmarks, TextUi ui) {
        String[] numberAndPage = information.split("/pg");

        try {
            if (numberAndPage.length == 1) {
                throw new QuotesifyException(ERROR_NO_PAGE_NUM);
            } else {

                String bookNumber = numberAndPage[0].trim();
                String page = numberAndPage[1].trim();
                int number = convertBookNumToInt(bookNumber);

                if (number > 0) {
                    Book bookToMark = books.findByNum(number);

                    if (bookToMark != null) {
                        addBookmarkToBook(bookToMark, bookmarks, page, ui);
                    } else {
                        System.out.println(ERROR_NO_BOOK_FOUND);
                    }
                }
            }
        } catch (QuotesifyException e) {
            ui.printErrorMessage(ERROR_NO_PAGE_NUM);
            addLogger.log(Level.INFO, "add bookmark to bookmarkList failed");
        }

    }

    /**
     * Add new bookmarks to the BookmarkList.
     *
     * @param book Referenced book of the bookmark.
     * @param bookmarks BookmarkList in Quotesify.
     * @param page Page number of the bookmark.
     * @param ui Ui of the program.
     */
    public void addBookmarkToBook(Book book, BookmarkList bookmarks, String page, TextUi ui) {
        Bookmark bookmarkToAdd = null;

        int pageNum = computePageNum(page);
        bookmarkToAdd = bookmarks.find(book);
        if (bookmarkToAdd == null && pageNum > -1) {
            bookmarkToAdd = createNewBookmark(bookmarks, book, pageNum);
            ui.printAddBookmark(bookmarkToAdd);
        } else if (bookmarkToAdd != null && pageNum > -1) {
            bookmarkToAdd = updateExistingBookmark(bookmarkToAdd, pageNum);
            ui.printUpdateBookmark(bookmarkToAdd);
        } else {
            addLogger.log(Level.INFO, "add bookmark to bookmarkList failed");
        }
    }

    /**
     * Create new bookmark objects to add tp the BookmarkList.
     *
     * @param bookmarks BookmarkList in Quotesify.
     * @parm book Referenced book of the bookmark.
     * @param pageNum Page number of the bookmark.
     * @return  Bookmark that is created.
     */
    public Bookmark createNewBookmark(BookmarkList bookmarks, Book book, int pageNum) {
        Bookmark newBookmark = new Bookmark(book, pageNum);
        bookmarks.add(newBookmark);
        return newBookmark;
    }

    /**
     * Update existing bookmarks to the BookmarkList.
     *
     * @param bookmark Existing bookmark object to be updated.
     * @param pageNum Page number of the bookmark.
     * @return Bookmark that is updated.
     */
    public Bookmark updateExistingBookmark(Bookmark bookmark, int pageNum) {
        bookmark.setPageNum(pageNum);
        return bookmark;
    }

    /**
     * Convert the page number from String to int.
     * Negative page number will be saved as -1 for further handling.
     *
     * @param information Page number in String.
     * @return Page number in int.
     */
    private int computePageNum(String information) {
        int pageNum = -1;
        try {
            pageNum = Integer.parseInt(information);
            if (pageNum <= 0) {
                throw new QuotesifyException(ERROR_NEGATIVE_PAGE_NUM);
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_PAGE_NUM);
        } catch (QuotesifyException e) {
            System.out.println(ERROR_NEGATIVE_PAGE_NUM);
        }

        return pageNum;
    }

    /**
     * Convert the book number from String to int.
     * Negative book number will be saved as -1 for further handling.
     *
     * @param information Book number in String.
     * @return Book number in int.
     */
    private int convertBookNumToInt(String information) {
        int bookNum = -1;
        try {
            bookNum = Integer.parseInt(information);
            if (bookNum <= 0) {
                throw new QuotesifyException(ERROR_NEGATIVE_BOOK_NUM);
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_BOOK_NUM);
        } catch (QuotesifyException e) {
            System.out.println(ERROR_NEGATIVE_BOOK_NUM);
        }

        return bookNum;
    }

    /**
     * Decides if the program should be terminated.
     *
     * @return decision to terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
