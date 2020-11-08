package seedu.duke.model;

import seedu.duke.exception.PaperTradeException;

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

    public void addToBookmarks(String symbol) throws PaperTradeException {
        if (bookmarkedStocks.size() == 5) {
            throw new PaperTradeException("Maximum of 5 bookmarked stocks reached!");
        }
        if (bookmarkedStocks.contains(symbol)) {
            throw new PaperTradeException("Already bookmarked this stock!");
        }
        bookmarkedStocks.add(symbol);
    }

    public void removeBookmark(String symbol) throws PaperTradeException {
        if (!bookmarkedStocks.contains(symbol)) {
            throw new PaperTradeException("This stock is not bookmarked!");
        }
        bookmarkedStocks.remove(symbol);
    }

}
