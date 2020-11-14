package anichan.commands;

import anichan.human.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.bookmark.Bookmark;
import anichan.exception.AniException;
import anichan.human.Workspace;
import anichan.parser.AddToWatchlistParser;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author michaeldinata
class AddToWatchlistCommandTest {
    private static AnimeData animeData;
    private static User user;
    private static StorageManager storageManager;
    private static Bookmark bookmark;
    private static Workspace workspace;

    private static final String STORAGE_DIRECTORY = "src" + File.separator + "test"
            + File.separator + "data" + File.separator;
    private static final String ZERO_ANIME_INDEX = "0";
    private static final String DUPLICATE_ANIME_INDEX = "1";
    private static final String LARGE_ANIME_INDEX = "3";
    private static final String VALID_ANIME_INDEX = "2";
    
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
        AddToWatchlistParser testParser = new AddToWatchlistParser();
        AddToWatchlistCommand testAdd = testParser.parse(ZERO_ANIME_INDEX);
        assertThrows(AniException.class, () -> {
            testAdd.execute(animeData, storageManager, user);
        });
    }

    @Test
    void execute_duplicateIndex_throwsAniException() throws AniException {
        AddToWatchlistParser testParser = new AddToWatchlistParser();
        AddToWatchlistCommand testAdd = testParser.parse(DUPLICATE_ANIME_INDEX);
        assertThrows(AniException.class, () -> {
            testAdd.execute(animeData, storageManager, user);
        });
    }

    @Test
    void execute_indexLargerThanDataSize_throwsAniException() throws AniException {
        AddToWatchlistParser testParser = new AddToWatchlistParser();
        AddToWatchlistCommand testAdd = testParser.parse(LARGE_ANIME_INDEX);
        assertThrows(AniException.class, () -> {
            testAdd.execute(animeData, storageManager, user);
        });
    }
    
    @Test
    void execute_validAnimeIndex_addAnimeSuccessful() throws AniException {
        String expectedOutput = "testAnime2 added to watchlist!";
        AddToWatchlistParser testParser = new AddToWatchlistParser();
        AddToWatchlistCommand testAdd = testParser.parse(VALID_ANIME_INDEX);
        String actualOutput = testAdd.execute(animeData, storageManager, user);
        assertEquals(expectedOutput, actualOutput);
    }
}
