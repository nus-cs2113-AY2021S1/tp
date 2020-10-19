package seedu.duke.watchlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.bookmark.Bookmark;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WatchlistTest {
    private Watchlist firstWatchlist;
    private Watchlist secondWatchlist;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> animeList = new ArrayList<>();
        animeList.add(1);
        animeList.add(2);

        firstWatchlist = new Watchlist("First Watchlist");
        secondWatchlist = new Watchlist("Second Watchlist", animeList);
    }

    @Test
    void testEqual() {
        // Test Equals on duplicaed named watchlist.
        Watchlist duplicateNameWatchlist = new Watchlist("First Watchlist");
        assertTrue(duplicateNameWatchlist.equals(firstWatchlist));
        assertFalse(duplicateNameWatchlist.equals(secondWatchlist));

        // Test Equals on non-Watchlist object.
        Bookmark bookmark = new Bookmark();
        assertFalse(firstWatchlist.equals(bookmark));

        // Test Equals on Null object.
        assertFalse(firstWatchlist.equals(null));
    }
}
