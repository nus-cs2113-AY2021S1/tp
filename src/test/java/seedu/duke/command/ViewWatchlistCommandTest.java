package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.parser.ViewWatchlistParser;
import seedu.duke.storage.StorageManager;
import seedu.duke.watchlist.Watchlist;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ViewWatchlistCommandTest {
    AnimeData animeData;
    User user;
    StorageManager storageManager;
    Bookmark bookmark;
    Workspace workspace;

    protected static final String ZERO_WATCHLIST_INDEX = "-v 0";
    protected static final String LARGE_WATCHLIST_INDEX = "-v 3";

    @BeforeEach
    void setUp() throws AniException {
        ArrayList<Anime> testList = new ArrayList<>();
        Anime testAnime1 = new Anime();
        Anime testAnime2 = new Anime();
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
}