package anichan.commands;

import anichan.anime.AnimeData;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.parser.WorkspaceParser;
import anichan.storage.StorageManager;
import anichan.watchlist.Watchlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import anichan.exception.AniException;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkspaceCommandTest {
    private static final String STORAGE_DIRECTORY = "src" + File.separator + "test"
            + File.separator + "data" + File.separator;

    private AnimeData animeData;
    private StorageManager storageManager;
    private User user;
    // private Workspace activeWorkspace;

    @BeforeEach
    void setUp() throws AniException {
        animeData = new AnimeData(new ArrayList<>());
        storageManager = new StorageManager(STORAGE_DIRECTORY);
        user = new User("Tom", "Male");

        Watchlist secondWatchlist = new Watchlist("Second2");
        secondWatchlist.addAnimeToList(1);
        secondWatchlist.addAnimeToList(2);

        Watchlist thirdWatchlist = new Watchlist("Third3");
        thirdWatchlist.addAnimeToList(3);

        ArrayList<Watchlist> watchlistList = new ArrayList<>();
        watchlistList.add(new Watchlist("First1"));
        watchlistList.add(secondWatchlist);
        watchlistList.add(thirdWatchlist);

        Workspace newWorkspace = user.addWorkspace("Default2");
        newWorkspace.setWatchlistList(watchlistList);
        user.setActiveWorkspace(newWorkspace);
        // activeWorkspace = user.getActiveWorkspace();
    }

    @Test
    void createWorkspace_validName_created() throws AniException {
        WorkspaceParser addParse = new WorkspaceParser();
        WorkspaceCommand testAddWorkspace = addParse.parse("-n Crunchy rail 12345");

        String expectedString = "Successfully added new workspace: Crunchy rail 12345";
        assertEquals(expectedString, testAddWorkspace.execute(animeData, storageManager, user));
    }

    @Test
    void switchWorkspace_validName_switched() throws AniException {
        // Create workspace
        WorkspaceParser addParse = new WorkspaceParser();
        WorkspaceCommand testAddWorkspace = addParse.parse("-n Crunchy rail 12345");
        testAddWorkspace.execute(animeData, storageManager, user);

        // Test switching workspace
        WorkspaceParser switchParse = new WorkspaceParser();
        WorkspaceCommand testSwitchWorkspace = switchParse.parse("-s Crunchy rail 12345");

        String expectedString = "Workspace switched to Crunchy rail 12345";

        assertEquals(expectedString, testSwitchWorkspace.execute(animeData, storageManager, user));
    }

    @Test
    void deleteWorkspace_validName_deleted() throws AniException {
        // Create two workspaces
        WorkspaceParser addParse = new WorkspaceParser();
        WorkspaceCommand testAddWorkspace = addParse.parse("-n Crunchy rail 12345");
        testAddWorkspace.execute(animeData, storageManager, user);

        // Test deleting workspace
        WorkspaceParser deleteParse = new WorkspaceParser();
        WorkspaceCommand testDeleteWorkspace = deleteParse.parse("-d Crunchy rail 12345");

        String expectedString = "Successfully deleted workspace: Crunchy rail 12345";

        assertEquals(expectedString, testDeleteWorkspace.execute(animeData, storageManager, user));
    }

    @Test
    void listWorkspace_empty_zeroWorkspace() throws AniException {
        WorkspaceParser listParse = new WorkspaceParser();
        WorkspaceCommand testListWorkspace = listParse.parse("-l");

        String expectedString = "Currently, you have 1 workspace(s):" + System.lineSeparator() + "1. Default2";

        assertEquals(expectedString, testListWorkspace.execute(animeData, storageManager, user));
    }
}
