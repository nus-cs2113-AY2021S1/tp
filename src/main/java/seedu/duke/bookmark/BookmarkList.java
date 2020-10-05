package seedu.duke.bookmark;

import java.util.ArrayList;

public class BookmarkList {

    private ArrayList<Bookmark> bookmarks;

    public BookmarkList() {
        this.bookmarks = new ArrayList<>();
    }

    public BookmarkList(ArrayList<String> bookmarks) {
        this.bookmarks = new ArrayList<>();
        loadBookmarks(bookmarks);
    }

    private void loadBookmarks(ArrayList<String> bookmarks) {
        // to be implemented
    }
}
