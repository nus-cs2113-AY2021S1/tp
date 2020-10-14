package seedu.quotesify.bookmark;

import seedu.quotesify.book.Book;
import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;

public class BookmarkList extends QuotesifyList<Bookmark> {
    private ArrayList<Bookmark> bookmarks = super.getList();

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
            if (bookmark.getBook().equals(book)) {
                return bookmark;
            }
        }

        return null;
    }

    public int getSize() {
        return bookmarks.size();
    }

    @Override
    public void delete(int index) {
        bookmarks.remove(index);
    }

    public void delete(Bookmark bookmark) {
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
