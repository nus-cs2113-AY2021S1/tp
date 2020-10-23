package anichan.watchlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

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
    void testEquals() {
        // Same object -> returns true.
        Watchlist duplicateNameWatchlist = new Watchlist("First Watchlist");
        assertTrue(firstWatchlist.equals(duplicateNameWatchlist));

        // Null -> returns false.
        assertFalse(firstWatchlist.equals(null));

        // Different type -> returns false.
        assertFalse(firstWatchlist.equals("testing"));

        // Different watchlist name object -> returns false.
        assertFalse(firstWatchlist.equals(secondWatchlist));
    }
}
