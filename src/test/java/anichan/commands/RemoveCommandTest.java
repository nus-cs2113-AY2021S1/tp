package anichan.commands;

import anichan.human.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.bookmark.Bookmark;
import anichan.exception.AniException;
import anichan.human.Workspace;
import anichan.parser.RemoveCommandParser;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author michaeldinata
class RemoveCommandTest {
    private static AnimeData animeData;
    private static User user;
    private static StorageManager storageManager;
    private static Bookmark bookmark;
    private static Workspace workspace;
    
    private static final String STORAGE_DIRECTORY = "src" + File.separator + "test"
            + File.separator + "data" + File.separator;
    private static final String ZERO_WATCHLIST_INDEX = "-d 0";
    private static final String LARGE_WATCHLIST_INDEX = "-d 3";
    private static final String EMPTY_WATCHLIST_INDEX = "-d 1";
    private static final String VALID_WATCHLIST_INDEX = "-d 1";

    @BeforeEach
    void setUp() throws AniException {
        ArrayList<Anime> testList = new ArrayList<>();
        String[] testAnime1ReleaseDate = {"2000", "12", "12"};
        String[] testAnime1Genre = {"Action", "Drama"};
        String[] testAnime2ReleaseDate = {"2020", "1", "1"};
        String[] testAnime2Genre = {"Thriller"};

        Anime testAnime1 = new Anime("testAnime1", testAnime1ReleaseDate, 80,
                testAnime1Genre, 20, 20);
        Anime testAnime2 = new Anime("testAnime2", testAnime2ReleaseDate, 65,
                testAnime2Genre, 45, 60);

        testList.add(testAnime1);
        testList.add(testAnime2);

        ArrayList<Integer> animeList = new ArrayList<>();
        animeList.add(0);
        Watchlist watchlist = new Watchlist("TestWatchlist", animeList);

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(watchlist);

        bookmark = new Bookmark();
        workspace = new Workspace("AddRemoveAnimeTest", watchlistList, bookmark);
        user = new User("TestUser", "Male");
        user.setActiveWorkspace(workspace);
        animeData = new AnimeData(testList);
        storageManager = new StorageManager(STORAGE_DIRECTORY);
    }

    @Test
    void execute_zeroInteger_throwsAniException() throws AniException {
        RemoveCommandParser testParser = new RemoveCommandParser();
        RemoveCommand testRemove = testParser.parse(ZERO_WATCHLIST_INDEX);
        assertThrows(AniException.class, () -> {
            testRemove.execute(animeData, storageManager, user);
        });
    }

    @Test
    void execute_indexLargerThanWatchlistSize_throwsAniException() throws AniException {
        RemoveCommandParser testParser = new RemoveCommandParser();
        RemoveCommand testRemove = testParser.parse(LARGE_WATCHLIST_INDEX);
        assertThrows(AniException.class, () -> {
            testRemove.execute(animeData, storageManager, user);
        });
    }
    
    @Test
    void execute_emptyWatchlist_throwsAniException() throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();
        Watchlist activeWatchlist = activeWorkspace.getActiveWatchlist();
        activeWatchlist.removeAnimeFromList(0);

        RemoveCommandParser testParser = new RemoveCommandParser();
        RemoveCommand testRemove = testParser.parse(EMPTY_WATCHLIST_INDEX);
        assertThrows(AniException.class, () -> {
            testRemove.execute(animeData, storageManager, user);
        });
    }
    
    @Test
    void execute_validWatchlistIndex_removeAnimeSuccessful() throws AniException {
        String expectedOutput = "testAnime1 successfully removed from watchlist!";
        RemoveCommandParser testParser = new RemoveCommandParser();
        RemoveCommand testRemove = testParser.parse(VALID_WATCHLIST_INDEX);
        String actualOutput = testRemove.execute(animeData, storageManager, user);
        assertEquals(expectedOutput, actualOutput);
    }
}
