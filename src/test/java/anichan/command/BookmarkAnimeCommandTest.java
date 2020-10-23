package anichan.command;

import anichan.anime.AnimeData;
import anichan.bookmark.Bookmark;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.parser.BookmarkParser;
import anichan.parser.BrowseParser;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookmarkAnimeCommandTest {
    private static AnimeData animeData;
    private static final String STORAGE_DIRECTORY = "src" + File.separator + "test"
            + File.separator + "data" + File.separator;
    private static StorageManager storageManager;
    private static User user;
    private static Workspace activeWorkspace;
    private static Bookmark bookmark;


    private static final String VALID_ADD_TEST = "-a 1";
    private static final String VALID_ADD_TEST2 = "-a 2";
    private final String VALID_LIST_TEST = "-l";
    private static final String VALID_DELETE_TEST = "-d 2";
    private static final String VALID_EDIT_TEST = "1 -e 1";
    private static final String VALID_NOTE_TEST = "1 -n test";
    private static final String VALID_SINGLE_INPUT_TEST = "1";
    private static final String INVALID_ADD_ANIME_INDEX_TEST = "-a 600";
    private static final String INVALID_DELETE_BOOKMARK_INDEX_TEST = "-d 5";
    private static final String INVALID_ADD_DUPLICATE_ANIME_INDEX_TEST = "-a 1";
    private static final String INVALID_EDIT_BOOKMARK_INDEX_TEST = "5 -e 5";


    @BeforeAll
    static void setUp() throws AniException {
        animeData = new AnimeData();
        storageManager = new StorageManager(STORAGE_DIRECTORY);
        user = new User("Mr Test", "Male");

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
        bookmark = activeWorkspace.getBookmark();
    }

    @Test
    @Order(1)
    void execute_validAddAnimeIndex_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(VALID_ADD_TEST);
        String result = testBookmarkCommand.execute(animeData, storageManager, user);
        System.out.println(result);
    }

    @Test
    @Order(2)
    void execute_validAddAnimeIndex2_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(VALID_ADD_TEST2);
        String result = testBookmarkCommand.execute(animeData, storageManager, user);
        System.out.println(result);
    }

    @Test
    @Order(3)
    void execute_validList_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(VALID_LIST_TEST);
        String result = testBookmarkCommand.execute(animeData, storageManager, user);
        System.out.println(result);
    }

    @Test
    @Order(4)
    void execute_validDelete_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(VALID_DELETE_TEST);
        String result = testBookmarkCommand.execute(animeData, storageManager, user);
        System.out.println(result);
    }

    @Test
    @Order(5)
    void execute_validEdit_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(VALID_EDIT_TEST);
        String result = testBookmarkCommand.execute(animeData, storageManager, user);
        System.out.println(result);
    }

    @Test
    @Order(6)
    void execute_validNote_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(VALID_NOTE_TEST);
        String result = testBookmarkCommand.execute(animeData, storageManager, user);
        System.out.println(result);
    }

    @Test
    @Order(7)
    void execute_validInfo_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(VALID_SINGLE_INPUT_TEST);
        String result = testBookmarkCommand.execute(animeData, storageManager, user);
        System.out.println(result);
    }

    @Test
    @Order(8)
    void execute_invalidAnimeIndexAdd_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(INVALID_ADD_ANIME_INDEX_TEST);

        assertThrows(AniException.class, () -> {
            testBookmarkCommand.execute(animeData, storageManager, user);
        });
    }

    @Test
    @Order(9)
    void execute_invalidBookmarkIndexDelete_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(INVALID_DELETE_BOOKMARK_INDEX_TEST);

        assertThrows(AniException.class, () -> {
            testBookmarkCommand.execute(animeData, storageManager, user);
        });
    }

    @Test
    @Order(10)
    void execute_invalidBookmarkIndexEdit_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(INVALID_ADD_DUPLICATE_ANIME_INDEX_TEST);

        assertThrows(AniException.class, () -> {
            testBookmarkCommand.execute(animeData, storageManager, user);
        });
    }

    @Test
    @Order(10)
    void execute_invalidDuplicateAnimeIndexAdd_Successful() throws AniException {
        BookmarkParser testParse = new BookmarkParser();
        BookmarkAnimeCommand testBookmarkCommand = testParse.parse(INVALID_EDIT_BOOKMARK_INDEX_TEST);

        assertThrows(AniException.class, () -> {
            testBookmarkCommand.execute(animeData, storageManager, user);
        });
    }
}
