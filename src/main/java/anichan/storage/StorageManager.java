package anichan.storage;

import anichan.bookmark.Bookmark;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.human.Workspace;
import anichan.logger.AniLogger;
import anichan.watchlist.Watchlist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageManager {
    private static final Logger LOGGER = AniLogger.getAniLogger(StorageManager.class.getName());

    private final String storageDirectory;
    private final UserStorage userStorage;
    private final WatchlistStorage watchlistStorage;
    private final BookmarkStorage bookmarkStorage;
    private final ScriptStorage scriptStorage;

    public StorageManager(String storageDirectory) {
        this.storageDirectory = storageDirectory;
        userStorage = new UserStorage(this.storageDirectory);
        watchlistStorage = new WatchlistStorage(this.storageDirectory);
        bookmarkStorage = new BookmarkStorage(this.storageDirectory);
        scriptStorage = new ScriptStorage(this.storageDirectory);
    }

    public String[] retrieveWorkspaceList() {
        File file = new File(storageDirectory);
        String[] workspaceList = file.list((current, name) -> new File(current, name).isDirectory());
        if (workspaceList == null) {
            LOGGER.log(Level.INFO, "Found 0 workspace.");
            return new String[0];
        }

        LOGGER.log(Level.INFO, "Found " + workspaceList.length + " workspace(s).");
        return workspaceList;
    }

    // ========================== Workspace Saving ==========================

    public void saveWorkspace(Workspace workspace) throws AniException {
        new File(storageDirectory + workspace.getName()).mkdirs();
        watchlistStorage.save(workspace.getName(), workspace.getWatchlistList());
    }

    // ========================== Workspace Deletion ==========================

    public void deleteWorkspace(String name) throws AniException {
        String deletePathString = storageDirectory + name;
        Path deletePath = Paths.get(deletePathString);

        try {
            Files.walk(deletePath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new AniException("Failed to delete workspace folder, you can try deleting manually.");
        }
    }

    // ========================== User Saving and Loading ==========================

    public void saveUser(User user) throws AniException {
        userStorage.save(user);
    }

    public User loadUser() throws AniException {
        return userStorage.load();
    }

    // ========================== Watchlist Saving and Loading ==========================

    public void saveWatchlistList(String workspaceName, ArrayList<Watchlist> watchlistList) throws AniException {
        watchlistStorage.save(workspaceName, watchlistList);
    }

    public String loadWatchlistList(String workspaceName, ArrayList<Watchlist> watchlistList) throws AniException {
        return watchlistStorage.load(workspaceName, watchlistList);
    }

    // ========================== Bookmark Saving and Loading ==========================

    public void saveBookmark(String workspaceName, Bookmark bookmark) throws AniException {
        bookmarkStorage.save(workspaceName, bookmark);
    }

    public String loadBookmark(String workspaceName, Bookmark bookmark) throws AniException {
        return bookmarkStorage.load(workspaceName, bookmark);
    }

    // ========================== Script Loading ==========================

    public String loadScript(String workspaceName, String fileName) throws AniException {
        return scriptStorage.readScript(workspaceName, fileName);
    }
}
