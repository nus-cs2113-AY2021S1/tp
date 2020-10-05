package seedu.duke.bookmark;

import seedu.duke.exception.DukeException;

import java.util.ArrayList;

public class BookmarkList {

    private ArrayList<Bookmark> bookmarks;

    public BookmarkList() {
        this.bookmarks = new ArrayList<>();
    }

    public BookmarkList(ArrayList<String> bookmarkString) {
        this.bookmarks = new ArrayList<>();
        loadBookmarkList(bookmarkString);
    }

    private void loadBookmarkList(ArrayList<String> bookmarks) {
        for (String line : bookmarks) {
            loadBookmarks(line);
        }
    }

    public void addBookmark(Bookmark bookmark)  {
        bookmarks.add(bookmark);
    }


    private void loadBookmarks(String line) {
        try {
            bookmarks.add(Bookmark.initBookmark(line));
        } catch (IndexOutOfBoundsException | DukeException e) {
            // Invalid task data, skips to the next entry
        }
    }

    /**
     * This method marks a task in the list as done.
     *
     * @param index The index of the task in the list to be marked as done.
     */
//    public void markTaskDone(int index) {
//        Task task = taskList.get(index);
//        task.markDone();
//    }

    /**
     * Returns the the data of all of the tasks in the list to be saved in the file.
     *
     * @return the data of all the tasks.
     */
    public String getData() {
        StringBuilder data = new StringBuilder();
        for (Bookmark bookmark : bookmarks) {
            data.append(bookmark.getExport()).append(System.lineSeparator());
        }
        return data.toString().trim();
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return the size of the taskList.
     */
    public int getSize() {
        return bookmarks.size();
    }

    /**
     * Returns task based on the index.
     *
     * @param index The index of the task in the list.
     * @return Task The task with the corresponding index in the list.
     */
    public Bookmark getBookmark(int index) {
        return bookmarks.get(index);
    }

    /**
     * Returns the taskList.
     *
     * @return The taskList.
     */
    public ArrayList<Bookmark> getBookmarkList() {
        return bookmarks;
    }

    /**
     * This method deletes the task from the list.
     *
     * @param bookmark The bookmark to be deleted.
     */
    public void deleteBookmark(Bookmark bookmark) {
        bookmarks.remove(bookmark);
    }


}
