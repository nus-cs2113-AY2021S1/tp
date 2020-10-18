package seedu.duke.storage;

import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.watchlist.Watchlist;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class StorageManager {
    private final String storageDirectory;

    UserStorage userStorage;
    WatchlistStorage watchlistStorage;
    BookmarkStorage bookmarkStorage;

    public StorageManager(String storageDirectory) {
        this.storageDirectory = storageDirectory;
        userStorage = new UserStorage(this.storageDirectory);
        watchlistStorage = new WatchlistStorage(this.storageDirectory);
        bookmarkStorage = new BookmarkStorage(this.storageDirectory);
    }

    public String[] retrieveWorkspaceList() {
        File file = new File(storageDirectory);
        String[] workspaceList = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        if (workspaceList == null) {
            return new String[0];
        }
        return workspaceList;
    }

    // ========================== User Saving and Loading ==========================

    public void saveUser(User user) throws AniException {
        userStorage.save(user);
    }

    public User loadUser() throws AniException {
        return userStorage.load();
    }

    // ========================== Workspace Saving and Loading ==========================

    public void saveWorkspace(Workspace workspace) throws AniException {
        new File(storageDirectory + workspace.getName()).mkdirs();
        watchlistStorage.save(workspace.getName(), workspace.getWatchlistList());
    }

    // ========================== Watchlist Saving and Loading ==========================

    public void saveWatchlistList(String workspaceName, ArrayList<Watchlist> watchlistList) throws AniException {
        watchlistStorage.save(workspaceName, watchlistList);
    }

    public String loadWatchlistList(String workspaceName, ArrayList<Watchlist> watchlistList) throws AniException {
        return watchlistStorage.load(workspaceName, watchlistList);
    }

    // ========================== Watchlist Saving and Loading ==========================

    public void saveBookmark(String workspaceName, Bookmark bookmark) throws AniException {
        bookmarkStorage.save(workspaceName, bookmark);
    }

    public String loadBookmark(String workspaceName, Bookmark bookmark) throws AniException {
        return bookmarkStorage.load(workspaceName, bookmark);
    }
}
