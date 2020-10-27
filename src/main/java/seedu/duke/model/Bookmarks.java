package seedu.duke.model;

import seedu.duke.exception.DukeException;

import java.io.Serializable;
import java.util.ArrayList;

public class Bookmarks implements Serializable {

    private ArrayList<String> bookmarkedStocks;

    public Bookmarks() {
        bookmarkedStocks = new ArrayList<>();
    }

    public Bookmarks(ArrayList<String> bookmarkedStocks) {
        this.bookmarkedStocks = bookmarkedStocks;
    }

    public ArrayList<String> getBookmarkedStocks() {
        return bookmarkedStocks;
    }

    public void addToBookmarks(String symbol) throws DukeException {
        if (bookmarkedStocks.contains(symbol)) {
            throw new DukeException("Already bookmarked this stock!");
        }
        bookmarkedStocks.add(symbol);
    }

    public void removeBookmark(String symbol) throws DukeException {
        if (!bookmarkedStocks.contains(symbol)) {
            throw new DukeException("This stock is not bookmarked!");
        }
        bookmarkedStocks.remove(symbol);
    }

}
