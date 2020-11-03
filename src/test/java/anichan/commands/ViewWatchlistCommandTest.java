package anichan.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.bookmark.Bookmark;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.parser.ViewWatchlistParser;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author michaeldinata
class ViewWatchlistCommandTest {
    AnimeData animeData;
    User user;
    StorageManager storageManager;
    Bookmark bookmark;
    Workspace workspace;

    protected static final String ZERO_WATCHLIST_INDEX = "0";
    protected static final String LARGE_WATCHLIST_INDEX = "3";
    protected static final String VALID_WATCHLIST_INDEX = "1";
    protected static final String VALID_EMPTY_INPUT1 = "";
    protected static final String VALID_EMPTY_INPUT2 = "   ";

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
        workspace = new Workspace("TestWorkspace", watchlistList, bookmark);
        user = new User("TestUser", "Male");
        user.setActiveWorkspace(workspace);
        animeData = new AnimeData(testList);
        storageManager = new StorageManager("test");
    }

    @Test
    void execute_zeroInteger_throwsAniException() throws AniException {
        ViewWatchlistParser testParser = new ViewWatchlistParser();
        ViewWatchlistCommand testView = testParser.parse(ZERO_WATCHLIST_INDEX);
        assertThrows(AniException.class, () -> {
            testView.execute(animeData, storageManager, user);
        });
    }

    @Test
    void execute_indexLargerThanDataSize_throwsAniException() throws AniException {
        ViewWatchlistParser testParser = new ViewWatchlistParser();
        ViewWatchlistCommand testView = testParser.parse(LARGE_WATCHLIST_INDEX);
        assertThrows(AniException.class, () -> {
            testView.execute(animeData, storageManager, user);
        });
    }
    
    @Test
    void execute_emptyWatchlist_throwsAniException() throws AniException {
        Workspace activeWorkspace = user.getActiveWorkspace();
        ArrayList<Watchlist> watchlistList = activeWorkspace.getWatchlistList();
        watchlistList.remove(0);
        
        ViewWatchlistParser testParser = new ViewWatchlistParser();
        ViewWatchlistCommand testView = testParser.parse(VALID_WATCHLIST_INDEX);
        assertThrows(AniException.class, () -> {
            testView.execute(animeData, storageManager, user);
        });
    }
    
    @Test
    void execute_validIndex_returnAnimeInWatchlist() throws AniException {
        StringBuilder expectedOutputBuild = new StringBuilder();
        expectedOutputBuild.append("Here are the anime in TestWatchlist watchlist:");
        expectedOutputBuild.append(System.lineSeparator());
        expectedOutputBuild.append("\t").append("1. testAnime1");
        String expectedOutput = expectedOutputBuild.toString();

        ViewWatchlistParser testParser1 = new ViewWatchlistParser();
        ViewWatchlistCommand testView1 = testParser1.parse(VALID_WATCHLIST_INDEX);
        String actualOutput1 = testView1.execute(animeData, storageManager, user);
        assertEquals(expectedOutput, actualOutput1);

        ViewWatchlistParser testParser2 = new ViewWatchlistParser();
        ViewWatchlistCommand testView2 = testParser2.parse(VALID_EMPTY_INPUT1);
        String actualOutput2 = testView2.execute(animeData, storageManager, user);
        assertEquals(expectedOutput, actualOutput2);

        ViewWatchlistParser testParser3 = new ViewWatchlistParser();
        ViewWatchlistCommand testView3 = testParser3.parse(VALID_EMPTY_INPUT2);
        String actualOutput3 = testView3.execute(animeData, storageManager, user);
        assertEquals(expectedOutput, actualOutput3);
    }
}
