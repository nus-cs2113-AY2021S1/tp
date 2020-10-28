package anichan.human;

import anichan.bookmark.Bookmark;
import anichan.watchlist.Watchlist;

import java.util.ArrayList;

public class Workspace {

    // ========================== Workspace Initialization ==========================

    private Bookmark bookmark;
    protected String workspaceName;
    private Watchlist activeWatchlist;
    private ArrayList<Watchlist> watchlistList;

    public Workspace(String workspaceName) {
        this.workspaceName = workspaceName;

        bookmark = new Bookmark();
        watchlistList = new ArrayList<>();
    }

    public Workspace(String workspaceName, ArrayList<Watchlist> watchlistList, Bookmark bookmark) {
        this.workspaceName = workspaceName;
        this.watchlistList = watchlistList;
        this.bookmark = bookmark;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public String getName() {
        return workspaceName;
    }

    @Override
    public String toString() {
        return workspaceName;
    }

    // ========================== Watchlist & Bookmark ==========================

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
