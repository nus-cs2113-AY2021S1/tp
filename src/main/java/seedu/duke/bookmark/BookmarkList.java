package seedu.duke.bookmark;

import seedu.duke.book.Book;
import seedu.duke.lists.QuotesifyList;

import java.util.ArrayList;

public class BookmarkList extends QuotesifyList<Bookmark> {
    private ArrayList<Bookmark> bookmarks;

    public BookmarkList() {
        super(new ArrayList<>());
    }
    public BookmarkList(ArrayList<Bookmark> bookmarks) {
        super(bookmarks);
    }

    public void add(Bookmark newBookmark) {
        bookmarks.add(newBookmark);
    }

    public Bookmark find(Book book) {
        for (Bookmark bookmark : bookmarks) {
            if(bookmark.getBook().equals(book)) {
                return bookmark;
            }
        }

        return null;
    }

    @Override
    public void delete(int index) {
        bookmarks.remove(index);
    }

    public void delete (Bookmark bookmark) {
        bookmarks.remove(bookmark);
    }

    @Override
    public String toString() {
        String bookmarksToReturn = "";
        for (Bookmark bookmark: bookmarks) {
            bookmarksToReturn += bookmark.toString() + System.lineSeparator();
        }

        return bookmarksToReturn;
    }
}
