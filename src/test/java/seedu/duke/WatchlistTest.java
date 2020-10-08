package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WatchlistTest {
    private Watchlist emptyWatchlist;

    @BeforeEach
    void setUp() {
        emptyWatchlist = new Watchlist("myWatchlist");
    }

    @Test
    void animeListInString_emptyAnimeList_returnsListIsEmptyMessage() {
        String expected = System.lineSeparator() + "Uhh.. It's empty.. :(" + System.lineSeparator();
        assertEquals(expected, emptyWatchlist.animeListInString());
    }

    @Test
    void toString_emptyAnimeList_returnsWatchlistNameWithListIsEmptyMessage() {
        String expected = "myWatchlist" + System.lineSeparator();
        expected += "Uhh.. It's empty.. :(" + System.lineSeparator();
        assertEquals(expected, emptyWatchlist.toString());
    }
}
