package seedu.duke.bookmark;

import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.commands.Command;
import seedu.duke.lists.ListManager;
import seedu.duke.ui.TextUi;

public class BookmarkCommand extends Command {
    private String type;
    private String information;

    public BookmarkCommand(String arguments) {
        String[] details = arguments.split(" ", 2);

        // if user did not provide arguments, let details[1] be empty string
        if (details.length == 1) {
            details = new String[]{details[0], ""};
        }
        type = details[0];
        information = details[1];
    }

    @Override
    public void execute(TextUi ui, ListManager listManager) {
        switch (type) {
        case TAG_BOOK:
            BookList books = (BookList) listManager.getList(ListManager.BOOK_LIST);
            BookmarkList bookmarks = (BookmarkList) listManager.getList(ListManager.BOOKMARK_LIST);
            handleBookmark(books, bookmarks, ui);
            break;
        default:
            break;
        }
    }

    public void handleBookmark(BookList books, BookmarkList bookmarks, TextUi ui) {
        String[] titleAndPage = information.split("/pg");
        String title = titleAndPage[0].trim();
        String page = titleAndPage[1].trim();
        Book bookToMark = null;

        bookToMark = books.findByTitle(title);
        if (bookToMark != null) {
            addBookmarkToBook(bookToMark, bookmarks, page, ui);
        }
        else {
            System.out.println(ERROR_NO_BOOK_FOUND);
        }

    }

    public void addBookmarkToBook(Book book, BookmarkList bookmarks, String page, TextUi ui) {
        Bookmark bookmarkToAdd = null;

        int pageNum = computePageNum(page);
        bookmarkToAdd = bookmarks.find(book);
        if (bookmarkToAdd == null){
            bookmarkToAdd = createNewBookmark(bookmarks, book, pageNum);
            ui.printAddBookmark(bookmarkToAdd);
        }
        else {
            bookmarkToAdd = updateExistingBookmark(bookmarkToAdd, pageNum);
            ui.printUpdateBookmark(bookmarkToAdd);
        }
    }

    public Bookmark createNewBookmark(BookmarkList bookmarks, Book book, int pageNum){
        Bookmark newBookmark = new Bookmark(book, pageNum);
        bookmarks.add(newBookmark);
        return newBookmark;
    }

    public Bookmark updateExistingBookmark(Bookmark bookmark, int pageNum){
        bookmark.setPageNum(pageNum);
        return bookmark;
    }

    private int computePageNum (String information) {
        int pageNum = -1;
        try {
            pageNum = Integer.parseInt(information);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_TODO_NUM);
        }

        return pageNum;
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
