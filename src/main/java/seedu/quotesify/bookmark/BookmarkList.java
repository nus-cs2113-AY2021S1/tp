package seedu.quotesify.bookmark;

import org.json.simple.JSONArray;
import seedu.quotesify.book.Book;
import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;

//@@author lunzard

/**
 * Represents a BookmarkList of bookmarks.
 */
public class BookmarkList extends QuotesifyList<Bookmark> {
    private ArrayList<Bookmark> bookmarks = super.getList();

    /**
     * Constructor for empty BookmarkList.
     */
    public BookmarkList() {
        super(new ArrayList<>());
    }

    /**
     * Constructor for BookmarkList with bookmarks.
     * @param bookmarks Bookmarks to be added into BookmarkList.
     */
    public BookmarkList(ArrayList<Bookmark> bookmarks) {
        super(bookmarks);
    }

    /**
     * Adds a new bookmark to the BookmarkList.
     *
     * @param newBookmark New bookmark to be added to the BookmarkList.
     */
    public void add(Bookmark newBookmark) {
        bookmarks.add(newBookmark);
    }

    /**
     * Finds a bookmark in the BookmarkList with the selected book.
     *
     * @param book Book of the bookmark.
     * @return Bookmark with the selected book.
     */
    public Bookmark find(Book book) {
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getBook().equals(book)) {
                return bookmark;
            }
        }

        return null;
    }

    /**
     * Finds a bookmark in the BookmarkList with the selected bookmark index.
     *
     * @param index index of the selected bookmark.
     * @return Bookmark with the selected index.
     */
    public Bookmark findByIndex(int index) {
        if (index <= bookmarks.size() - 1) {
            return bookmarks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Returns the number of bookmarks the BookmarkList has.
     *
     * @return Number of bookmarks in the BookmarkList.
     */
    public int getSize() {
        return bookmarks.size();
    }

    /**
     * Deletes a bookmark from the BookmarkList, specified by its index.
     *
     * @param index Bookmark index in the BookmarkList.
     */
    @Override
    public void delete(int index) {
        bookmarks.remove(index);
    }

    /**
     * Deletes a bookmark from the BookmarkList, specified by the bookmark object.
     *
     * @param bookmark Bookmark object to be deleted.
     */
    public void delete(Bookmark bookmark) {
        bookmarks.remove(bookmark);
    }

    /**
     * Converts the BookmarkList into a String.
     *
     * @return String of bookmarks in BookmarkList.
     */
    @Override
    public String toString() {
        String bookmarksToReturn = "";
        int index = 0;
        for (Bookmark bookmark: bookmarks) {
            index++;
            bookmarksToReturn += index + ". " + bookmark.toString() + System.lineSeparator();
        }

        return bookmarksToReturn;
    }

    /**
     * Converts the BookmarkList into a JSONArray.
     *
     * @return JSONArray of BookmarkList.
     */
    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Bookmark bookmark : bookmarks) {
            list.add(bookmark.toJson());
        }
        return list;
    }
}
