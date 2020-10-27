package anichan.commands;

import anichan.human.User;
import anichan.human.Workspace;
import anichan.watchlist.Watchlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.storage.StorageManager;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WatchlistCommandTest {
    private static final String STORAGE_DIRECTORY = "src" + File.separator + "test"
                                                    + File.separator + "data" + File.separator;

    private AnimeData animeData;
    private StorageManager storageManager;
    private User user;
    private Workspace activeWorkspace;

    @BeforeEach
    void setUp() throws AniException {
        animeData = new AnimeData(new ArrayList<>());
        storageManager = new StorageManager(STORAGE_DIRECTORY);
        user = new User("Testing", "Male");

        Watchlist secondWatchlist = new Watchlist("Second");
        secondWatchlist.addAnimeToList(1);
        secondWatchlist.addAnimeToList(2);

        Watchlist thirdWatchlist = new Watchlist("Third");
        thirdWatchlist.addAnimeToList(3);

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("First"));
        watchlistList.add(secondWatchlist);
        watchlistList.add(thirdWatchlist);

        Workspace newWorkspace = user.addWorkspace("Default");
        newWorkspace.setWatchlistList(watchlistList);
        user.setActiveWorkspace(newWorkspace);
        activeWorkspace = user.getActiveWorkspace();
    }

    // ========================== General ==========================

    @Test
    void execute_invalidOption_throwsAniException() {
        // Invalid option
        WatchlistCommand invalidOption = new WatchlistCommand("invalid", "invalid", 0);
        assertThrows(AniException.class, () -> invalidOption.execute(animeData, storageManager, user));
    }

    @Test
    void execute_nullParameters_throwsAssertionError() {
        // Null option, watchlist name, and watchlist index
        WatchlistCommand nullOption = new WatchlistCommand(null, null, 0);
        assertThrows(AssertionError.class, () -> nullOption.execute(animeData, storageManager, user));

        // Null WatchlistList
        WatchlistCommand nullWatchlistList = new WatchlistCommand("n", "First", 0);
        activeWorkspace.setWatchlistList(null);
        assertThrows(AssertionError.class, () -> nullWatchlistList.execute(animeData, storageManager, user));
    }

    // ========================== Create ==========================

    @Test
    void execute_validParametersForCreateWatchlist_success() throws AniException {
        WatchlistCommand createWatchlist = new WatchlistCommand("n", "Test", 0);
        createWatchlist.execute(animeData, storageManager, user);
        assertEquals(4, activeWorkspace.getWatchlistList().size());
    }

    @Test
    void execute_notUniqueWatchlistNameForCreateWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("n", "First", 0);
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_watchlistNameMoreThan30CharactersForCreateWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("n",
                "averylongwatchnamethatwouldfail",
                0);
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    // ========================== List ==========================

    @Test
    void execute_validParametersForListWatchlistList_success() throws AniException {
        WatchlistCommand listWatchlistList = new WatchlistCommand("l", "", 0);
        String emptyListMessage = "Uhh.. You have no watchlist to list..";
        assertNotEquals(emptyListMessage, listWatchlistList.execute(animeData, storageManager, user));
    }

    @Test
    void execute_emptyWatchlistListForListWatchlistList_emptyListMessage() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("l", "", 0);
        activeWorkspace.setWatchlistList(new ArrayList<>());
        String expected = "Uhh.. You have no watchlist..";
        assertEquals(expected, watchlistCommand.execute(animeData, storageManager, user));
    }

    // ========================== Select ==========================

    @Test
    void execute_validParametersForSelectWatchlistList_success() throws AniException {
        WatchlistCommand selectWatchlist = new WatchlistCommand("s", "", 3);
        selectWatchlist.execute(animeData, storageManager, user);
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        Watchlist selectedWatchlist = activeWorkspace.getWatchlistList().get(2);
        assertEquals(activeWatchlist, selectedWatchlist);
    }

    @Test
    void execute_emptyWatchlistListForSelectWatchlist_throwsAniException() {
        activeWorkspace.setWatchlistList(new ArrayList<>());
        WatchlistCommand selectWatchlist = new WatchlistCommand("s", "", 1);
        assertThrows(AniException.class, () -> selectWatchlist.execute(animeData, storageManager, user));
    }

    @Test
    void execute_invalidWatchlistIndexForSelectWatchlist_throwsAniException() {
        WatchlistCommand selectWithNegativeIndex = new WatchlistCommand("s", "", -2);
        assertThrows(AniException.class, () -> selectWithNegativeIndex.execute(animeData, storageManager, user));

        WatchlistCommand deleteWithOutOfRangeIndex = new WatchlistCommand("s", "", 999);
        assertThrows(AniException.class, () -> deleteWithOutOfRangeIndex.execute(animeData, storageManager, user));
    }

    @Test
    void execute_onlyOneWatchlistForSelectWatchlist_throwsAniException() {
        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Only One"));
        activeWorkspace.setWatchlistList(watchlistList);
        activeWorkspace.setActiveWatchlist(watchlistList.get(0));

        WatchlistCommand selectWatchlist = new WatchlistCommand("s", "", 1);
        assertThrows(AniException.class, () -> selectWatchlist.execute(animeData, storageManager, user));
    }

    // ========================== Delete ==========================

    @Test
    void execute_validParametersForDeleteWatchlistList_success() throws AniException {
        // Delete active watchlist
        activeWorkspace.setActiveWatchlist(activeWorkspace.getWatchlistList().get(2));
        WatchlistCommand deleteActiveWatchlist = new WatchlistCommand("d", "", 3);
        deleteActiveWatchlist.execute(animeData, storageManager, user);
        Assertions.assertEquals(activeWorkspace.getWatchlistList().get(0), activeWorkspace.getActiveWatchlist());

        // Delete non-active watchlist
        WatchlistCommand deleteWatchlist = new WatchlistCommand("d", "", 2);
        deleteWatchlist.execute(animeData, storageManager, user);
        assertEquals(1, activeWorkspace.getWatchlistList().size());
    }

    @Test
    void execute_emptyWatchlistListForDeleteWatchlist_throwsAniException() {
        activeWorkspace.setWatchlistList(new ArrayList<>());
        WatchlistCommand deleteWatchlist = new WatchlistCommand("d", "", 1);
        assertThrows(AniException.class, () -> deleteWatchlist.execute(animeData, storageManager, user));
    }

    @Test
    void execute_invalidWatchlistIndexForDeleteWatchlist_throwsAniException() {
        WatchlistCommand deleteWithNegativeIndex = new WatchlistCommand("d", "", -2);
        assertThrows(AniException.class, () -> deleteWithNegativeIndex.execute(animeData, storageManager, user));

        WatchlistCommand selectWithOutOfRangeIndex = new WatchlistCommand("d", "", 999);
        assertThrows(AniException.class, () -> selectWithOutOfRangeIndex.execute(animeData, storageManager, user));
    }

    @Test
    void execute_onlyOneWatchlistForDeleteWatchlist_throwsAniException() {
        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("Only One"));
        activeWorkspace.setWatchlistList(watchlistList);
        activeWorkspace.setActiveWatchlist(watchlistList.get(0));

        WatchlistCommand deleteWatchlist = new WatchlistCommand("d", "", 1);
        assertThrows(AniException.class, () -> deleteWatchlist.execute(animeData, storageManager, user));
    }
}
