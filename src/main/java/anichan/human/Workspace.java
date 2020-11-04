package anichan.human;

import anichan.bookmark.Bookmark;
import anichan.watchlist.Watchlist;

import java.util.ArrayList;

/**
 * Represents the Workspace of User.
 */
public class Workspace {

    // ========================== Workspace Initialization ==========================

    private static final String ASSERTION_INVALID_MESSAGE = "Input should not be null.";
    private Bookmark bookmark;
    private final String workspaceName;
    private Watchlist activeWatchlist;
    private ArrayList<Watchlist> watchlistList;

    /**
     * Creates an instance of a Workspace.
     *
     * @param workspaceName name of new Workspace
     */
    public Workspace(String workspaceName) {
        assert workspaceName != null : ASSERTION_INVALID_MESSAGE;
        this.workspaceName = workspaceName;

        bookmark = new Bookmark();
        watchlistList = new ArrayList<>();
    }

    /**
     * Creates an instance of a Workspace.
     *
     * @param workspaceName name of new Workspace
     */
    public Workspace(String workspaceName, ArrayList<Watchlist> watchlistList, Bookmark bookmark) {
        this.workspaceName = workspaceName;
        this.watchlistList = watchlistList;
        this.bookmark = bookmark;
    }

    /**
     * Gets name of the Workspace.
     *
     * @return name of Workspace
     */
    public String getName() {
        return workspaceName;
    }

    @Override
    public String toString() {
        return workspaceName;
    }

    // ========================== Watchlist & Bookmark ==========================

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setActiveWatchlist(Watchlist activeWatchlist) {
        this.activeWatchlist = activeWatchlist;
    }

    public void setWatchlistList(ArrayList<Watchlist> watchlistList) {
        this.watchlistList = watchlistList;
    }

    public Watchlist getActiveWatchlist() {
        return activeWatchlist;
    }

    public String getActiveWatchlistName() {
        return activeWatchlist.getName();
    }

    public ArrayList<Watchlist> getWatchlistList() {
        return watchlistList;
    }
}
