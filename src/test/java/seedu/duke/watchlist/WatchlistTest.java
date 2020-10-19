package seedu.duke.watchlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WatchlistTest {
    private Watchlist emptyWatchlist;
    private Watchlist filledWatchlist;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> animeList = new ArrayList<>();
        animeList.add(0);
        animeList.add(2);
        animeList.add(3);

        emptyWatchlist = new Watchlist("emptyWatchlist");
        filledWatchlist = new Watchlist("filledWatchlist", animeList);
    }

    @Test
    void testAnimeListToStringWithEmptyWatchlist() {
        String expected = "Uhh.. It's empty.. :(" + System.lineSeparator();
        assertEquals(emptyWatchlist.animeListToString(), expected);
    }

    @Test
    void testAnimeListToStringWithFilledWatchlist() {
        String expected = "1. 0" + System.lineSeparator();
        expected += "2. 2" + System.lineSeparator();
        expected += "3. 3" + System.lineSeparator();
        assertEquals(filledWatchlist.animeListToString(), expected);
    }

    @Test
    void testToStringWithEmptyWatchlist() {
        String expected = "emptyWatchlist" + System.lineSeparator();
        expected += "Uhh.. It's empty.. :(" + System.lineSeparator();
        assertEquals(emptyWatchlist.toString(), expected);
    }

    @Test
    void testToStringWithFilledWatchlist() {
        String expected = "filledWatchlist" + System.lineSeparator();
        expected += "1. 0" + System.lineSeparator();
        expected += "2. 2" + System.lineSeparator();
        expected += "3. 3" + System.lineSeparator();
        assertEquals(filledWatchlist.toString(), expected);
    }

    @Test
    void testEqual_duplicateWatchlist_returnsTrue() {
        Watchlist duplicateEmptyWatchlist = new Watchlist("emptyWatchlist");
        assertTrue(emptyWatchlist.equals(duplicateEmptyWatchlist));
    }

    @Test
    void testEqual_uniqueWatchlist_returnsFalse() {
        assertFalse(emptyWatchlist.equals(filledWatchlist));
    }
}
