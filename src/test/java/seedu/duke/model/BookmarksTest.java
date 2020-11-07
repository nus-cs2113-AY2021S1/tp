package seedu.duke.model;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.PaperTradeException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BookmarksTest {

    @Test
    void addToBookmarks_bookmarkedStock_expectException() {
        String symbol = "aapl";
        ArrayList<String> bookmarkedStocks = new ArrayList<>();
        bookmarkedStocks.add(symbol);
        Bookmarks bookmarks = new Bookmarks(bookmarkedStocks);
        assertThrows(PaperTradeException.class, () -> {
            bookmarks.addToBookmarks(symbol);
        });
    }

    @Test
    void removeBookmark_unbookmarkedStock_expectException() {
        String symbol = "aapl";
        ArrayList<String> bookmarkedStocks = new ArrayList<String>();
        Bookmarks bookmarks = new Bookmarks(bookmarkedStocks);
        assertThrows(PaperTradeException.class, () -> {
            bookmarks.removeBookmark(symbol);
        });
    }

}
