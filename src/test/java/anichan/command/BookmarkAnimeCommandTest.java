package anichan.command;

import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BookmarkAnimeCommandTest {
    AnimeData animeData;
    private static final String STORAGE_DIRECTORY = "src" + File.separator + "test"
            + File.separator + "data" + File.separator;
    private StorageManager storageManager;
    private User user;
    private Workspace activeWorkspace;

    @BeforeEach
    void setUp() throws AniException {
        animeData = new AnimeData();
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

}
