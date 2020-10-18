package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.storage.StorageManager;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WatchlistCommandTest {
    AnimeData animeData;
    StorageManager storageManager;
    User user;
    Workspace activeWorkspace;

    @BeforeEach
    void setUp() throws AniException {
        animeData = new AnimeData(new ArrayList<>());
        storageManager = new StorageManager();
        user = new User("Testing", "Male");

        Watchlist secondWatchlist = new Watchlist("Second");
        secondWatchlist.addAnimeToList("The Slayers Next");
        secondWatchlist.addAnimeToList("Pokémon");

        Watchlist thirdWatchlist = new Watchlist("Third");
        thirdWatchlist.addAnimeToList("Naruto");

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("First"));
        watchlistList.add(secondWatchlist);
        watchlistList.add(thirdWatchlist);

        Workspace newWorkspace = user.addWorkspace("Default");
        newWorkspace.setWatchlistList(watchlistList);
        newWorkspace.setActiveWatchlist(newWorkspace.getWatchlistList().get(0));
        user.setActiveWorkspace(newWorkspace);
        activeWorkspace = user.getActiveWorkspace();
    }

    // ========================== General ==========================
    @Test
    void execute_invalidParameters_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_unknownOption_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-t test");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    // ========================== Create ==========================

    @Test
    void execute_validParametersForCreateWatchlist_success() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-n Test");
        watchlistCommand.execute(animeData, storageManager, user);
        assertEquals(4, activeWorkspace.getWatchlistList().size());
    }

    @Test
    void execute_noWatchlistNameSpecifiedForCreateWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-n");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_duplicateWatchlistNameForCreateWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-n First");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    // ========================== List ==========================

    @Test
    void execute_validParametersForListAllWatchlist_success() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-l");
        String expected = "Uhh.. You have no watchlist to list..";
        assertNotEquals(expected, watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_emptyWatchlistListForListAllWatchlist_emptyWatchlistListMessage() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-l");
        activeWorkspace.setWatchlistList(new ArrayList<>());
        String expected = "Uhh.. You have no watchlist to list..";
        assertEquals(expected, watchlistCommand.execute(animeData, storageManager, user));
    }

    // ========================== Select ==========================

    @Test
    void execute_validParametersForSelectWatchlist_success() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-s 2");       // one-based numbering
        watchlistCommand.execute(animeData, storageManager, user);
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        Watchlist selectedWatchlist = activeWorkspace.getWatchlistList().get(1);         // zero-based numbering
        assertEquals(activeWatchlist, selectedWatchlist);
    }

    @Test
    void execute_emptyWatchlistListForSelectWatchlist_emptyWatchlistListMessage() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-s 1");
        activeWorkspace.setWatchlistList(new ArrayList<>());
        String expected = "Uhh.. You have no watchlist to select..";
        assertEquals(expected, watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_notIntegerStringForSelectWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-s one");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_negativeWatchlistIndexForSelectWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-s -2");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_invalidWatchlistIndexForSelectWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-s 999");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_watchlistIndexIsCurrentActiveWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-s 1");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    // ========================== Delete ==========================

    @Test
    void execute_validParametersForDeleteWatchlist_success() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-d 2");
        watchlistCommand.execute(animeData, storageManager, user);
        assertEquals(2, activeWorkspace.getWatchlistList().size());
    }

    @Test
    void execute_emptyWatchlistListForDeleteWatchlist_emptyWatchlistListMessage() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-d 1");
        activeWorkspace.setWatchlistList(new ArrayList<>());
        String expected = "Uhh.. You have no watchlist to delete..";
        assertEquals(expected, watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_notIntegerStringForDeleteWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-d one");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_negativeWatchlistIndexForDeleteWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-d -2");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_invalidWatchlistIndexForDeleteWatchlist_throwsAniException() {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-d 999");
        assertThrows(AniException.class, () -> watchlistCommand.execute(animeData, storageManager, user));
    }

    @Test
    void execute_currentActiveWatchlistIndexForDeleteWatchlist() throws AniException {
        WatchlistCommand watchlistCommand = new WatchlistCommand("-d 3");      // one-based numbering
        activeWorkspace.setActiveWatchlist(activeWorkspace.getWatchlistList().get(2));  // zero-based numbering
        watchlistCommand.execute(animeData, storageManager, user);
        assertEquals(activeWorkspace.getWatchlistList().get(0), activeWorkspace.getActiveWatchlist());
    }
}