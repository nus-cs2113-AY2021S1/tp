package anichan.watchlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//@@author OngDeZhi
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
    void equals() {
        // Same object -> returns true.
        Watchlist duplicateNameWatchlist = new Watchlist("First Watchlist");
        assertEquals(firstWatchlist, duplicateNameWatchlist);

        // Null -> returns false.
        assertNotEquals(firstWatchlist, null);

        // Different type -> returns false.
        assertNotEquals(firstWatchlist, "testing");

        // Different watchlist name object -> returns false.
        assertNotEquals(firstWatchlist, secondWatchlist);
    }
}
