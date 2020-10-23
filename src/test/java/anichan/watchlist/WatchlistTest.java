package anichan.watchlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import anichan.bookmark.Bookmark;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WatchlistTest {
    private static final int SAMPLE_ANIME_INDEX_ONE = 1;
    private static final int SAMPLE_ANIME_INDEX_TWO = 2;

    private Watchlist firstWatchlist;
    private Watchlist secondWatchlist;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> animeList = new ArrayList<>();
        animeList.add(SAMPLE_ANIME_INDEX_ONE);
        animeList.add(SAMPLE_ANIME_INDEX_TWO);

        firstWatchlist = new Watchlist("First Watchlist");
        secondWatchlist = new Watchlist("Second Watchlist", animeList);
    }

    @Test
    void testEqual() {
        // Test Equals on duplicated named watchlist.
        Watchlist duplicateNameWatchlist = new Watchlist("First Watchlist");
        assertEquals(duplicateNameWatchlist, firstWatchlist);
        assertNotEquals(secondWatchlist, duplicateNameWatchlist);

        // Test Equals on non-Watchlist object.
        Bookmark bookmark = new Bookmark();
        assertNotEquals(bookmark, firstWatchlist);

        // Test Equals on Null object.
        assertNotEquals(firstWatchlist, null);
    }
}
