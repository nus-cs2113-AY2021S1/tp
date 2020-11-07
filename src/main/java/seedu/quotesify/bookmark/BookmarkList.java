package seedu.quotesify.bookmark;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seedu.quotesify.book.Book;
import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;

//@@author lunzard
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

    public Bookmark findByIndex(int index) {
        if (index <= bookmarks.size() - 1) {
            return bookmarks.get(index);
        } else {
            return null;
        }
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
        int index = 0;
        for (Bookmark bookmark: bookmarks) {
            index++;
            bookmarksToReturn += index + ". " + bookmark.toString() + System.lineSeparator();
        }

        return bookmarksToReturn;
    }

    //@@author
    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Bookmark bookmark : bookmarks) {
            list.add(bookmark.toJson());
        }
        return list;
    }
}
