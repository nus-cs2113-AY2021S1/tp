package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WatchlistTest {

    @Test
    void animeListInString_EmptyAnimeList_printListIsEmptyMessage() {
        Watchlist watchlist = new Watchlist("myWatchlist");
        String expected = System.lineSeparator() + "Uhh.. It's empty.. :(" + System.lineSeparator();
        assertEquals(expected, watchlist.animeListInString());
    }

    @Test
    void toString_EmptyAnimeList_printWatchlistNameAndListIsEmptyMessage() {
        Watchlist watchlist = new Watchlist("myWatchlist");
        String expected = "myWatchlist" + System.lineSeparator();
        expected += "Uhh.. It's empty.. :(" + System.lineSeparator();;
        assertEquals(expected, watchlist.toString());
    }
}