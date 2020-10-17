package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.human.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WatchlistCommandTest {
    AnimeData animeData;
    User user;

    //@@author OngDeZhi
    //    @BeforeEach
    //    void setUp() throws ParseException, AniException {
    //        animeData = new AnimeData(new ArrayList<>());
    //        Storage storage = new Storage("profile-test.txt", "watchlist-test.txt");
    //        user = new User(storage);
    //
    //        Workspace activeWorkspace = new Workspace("Testing", "Male");
    //        user.setActiveWorkspace(activeWorkspace);
    //
    //        Watchlist secondWatchlist = new Watchlist("Second");
    //        secondWatchlist.addAnimeToList("The Slayers Next");
    //        secondWatchlist.addAnimeToList("Pokémon");
    //
    //        ArrayList<Watchlist> watchlists = activeWorkspace.getWatchlistList();
    //        watchlists.add(secondWatchlist);
    //        activeWorkspace.setWatchlistList(watchlists);
    //    }

    //    @Test
    //    void execute_invalidParameters_throwsAniException() {
    //        WatchlistCommand watchlistCommand = new WatchlistCommand("");
    //        assertThrows(AniException.class, () -> {
    //            watchlistCommand.execute(animeData, user);
    //        });
    //    }

    //    @Test
    //    void execute_unknownOption_throwsAniException() {
    //        WatchlistCommand watchlistCommand = new WatchlistCommand("-t test");
    //        assertThrows(AniException.class, () -> {
    //            watchlistCommand.execute(animeData, user);
    //        });
    //    }

    //    @Test
    //    void execute_validParametersForCreateWatchlist_success() throws AniException {
    //        WatchlistCommand watchlistCommand = new WatchlistCommand("-n Test");
    //        String expected = "Watchlist created successfully!";
    //        assertEquals(watchlistCommand.execute(animeData, user), expected);
    //    }

    //    @Test
    //    void execute_invalidWatchlistNameForCreateWatchlist_throwsAniException() {
    //        WatchlistCommand watchlistCommand = new WatchlistCommand("-n");
    //        assertThrows(AniException.class, () -> {
    //            watchlistCommand.execute(animeData, user);
    //        });
    //    }

    //    @Test
    //    void execute_duplicateWatchlistNameForCreateWatchlist_success() {
    //        WatchlistCommand watchlistCommand = new WatchlistCommand("-n Default");
    //        assertThrows(AniException.class, () -> {
    //            watchlistCommand.execute(animeData, user);
    //        });
    //    }

    //    @Test
    //    void execute_validParametersForListAllWatchlist_success() throws AniException {
    //        WatchlistCommand watchlistCommand = new WatchlistCommand("-l");
    //        String expected = "Currently, you have 2 watchlist(s):" + System.lineSeparator();
    //        expected += "\t1. Default" + System.lineSeparator();
    //        expected += "\t2. Second";
    //        assertEquals(watchlistCommand.execute(animeData, user), expected);
    //    }
}