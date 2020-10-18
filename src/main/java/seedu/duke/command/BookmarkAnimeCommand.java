package seedu.duke.command;

import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.storage.StorageManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BookmarkAnimeCommand extends Command {

    private int bookmarkIndex;
    private int animeIndex;
    private int bookmarkEpisode;
    // e for edit, a for add, d for delete
    private String bookmarkAction;
    private static final Logger LOGGER = Logger.getLogger(BookmarkAnimeCommand.class.getName());

    public BookmarkAnimeCommand() {
        // Set log levels
        LOGGER.setLevel(Level.WARNING);
        LOGGER.log(Level.INFO, "Successfully loaded fields for Bookmark command.");
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        String result = "";
        Bookmark bookmark = user.getActiveWorkspace().bookmark;

        switch (bookmarkAction) {
        case "e":
            LOGGER.log(Level.INFO, "Executing Edit Episode.");
            result = editBookmarkEpisode(animeData, storageManager, user, bookmark);
            break;
        case "a":
            LOGGER.log(Level.INFO, "Executing Add Anime to Bookmark.");
            result = addBookmarkEntry(animeData, storageManager, user, bookmark);
            break;
        case "d":
            LOGGER.log(Level.INFO, "Executing Delete Anime from Bookmark.");
            result = deleteBookmarkEntry(animeData, storageManager, user, bookmark);
            break;
        case "l":
            LOGGER.log(Level.INFO, "Executing List all anime in Bookmark.");
            result = "Listing all anime in bookmark:";
            String bookmarkList = listBookmarkEntries(animeData, bookmark);
            result += bookmarkList;
            break;
        default:
            break;
        }
        LOGGER.log(Level.INFO, "Execute Bookmark command successful.");
        return result;
    }

    private String listBookmarkEntries(AnimeData animeData, Bookmark bookmark) {
        String bookmarkList = bookmark.animeListInString(animeData);
        return bookmarkList;
    }

    private String deleteBookmarkEntry(AnimeData animeData, StorageManager storageManager,
                                       User user, Bookmark bookmark) throws AniException {
        String result;
        if (bookmarkIndex > bookmark.getBookmarkSize() || bookmarkIndex <= 0) {
            String invalidBookmarkIndex = "Bookmark index " + bookmarkIndex + "provided is invalid."
                    + System.lineSeparator() + " Bookmark index is outside Bookmark range (too big or too small).";
            LOGGER.log(Level.WARNING, "Bookmark command execute failed:" + invalidBookmarkIndex);
            throw new AniException(invalidBookmarkIndex);
        }
        Anime animeToDelete = bookmark.getAnimeBookmarkByIndex(animeData, bookmarkIndex - 1);
        result = "Removing " + animeToDelete.getAnimeName() + "! :(";
        bookmark.removeAnimeBookmark(bookmarkIndex - 1);
        //storageManager.saveBookmark(user.getActiveWorkspace().getName(), bookmark);
        return result;
    }


    private String addBookmarkEntry(AnimeData animeData, StorageManager storageManager,
                                    User user, Bookmark bookmark) throws AniException {
        String result;
        if (animeIndex > animeData.getSize() || animeIndex <= 0) {
            String invalidAnimeIndex = "Anime index " + animeIndex + "provided is invalid."
                    + System.lineSeparator() + " Anime index is outside AnimeData range (too big or too small).";
            LOGGER.log(Level.WARNING, "Bookmark command execute failed:" + invalidAnimeIndex);
            throw new AniException(invalidAnimeIndex);
        }

        Anime animeToAdd = animeData.getAnimeByID(animeIndex - 1);
        result = "Saving " + animeToAdd.getAnimeID() + ". " + animeToAdd.getAnimeName() + " " + " to bookmark.";
        bookmark.addAnimeBookmark(animeIndex - 1);
        //storageManager.saveBookmark(user.getActiveWorkspace().getName(), bookmark);
        return result;
    }

    private String editBookmarkEpisode(AnimeData animeData, StorageManager storageManager,
                                       User user, Bookmark bookmark) throws AniException {

        if (bookmarkIndex > bookmark.getBookmarkSize() || bookmarkIndex <= 0) {
            String invalidBookmarkIndex = "Bookmark index " + bookmarkIndex + " provided is invalid."
                    + System.lineSeparator() + " Bookmark index is outside Bookmark range (too big or too small).";
            LOGGER.log(Level.WARNING, "Bookmark command execute failed:" + invalidBookmarkIndex);
            throw new AniException(invalidBookmarkIndex);
        }
        String result;
        assert bookmarkEpisode >= 0 : "bookmarkEpisode should be positive";
        bookmark.editAnimeBookmarkEpisode(bookmarkIndex - 1, bookmarkEpisode);
        Anime animeToEdit = bookmark.getAnimeBookmarkByIndex(animeData, bookmarkIndex - 1);
        result = "Editing " + animeToEdit.getAnimeName() + " to have " + bookmarkEpisode + " episode";
        //storageManager.saveBookmark(user.getActiveWorkspace().getName(), bookmark);
        return result;
    }

    public String getBookmarkAction() {
        return this.bookmarkAction;
    }

    public void setBookmarkAction(String actionString) {
        this.bookmarkAction = actionString;
    }

    public boolean setBookmarkIndex(String bookmarkIndexString) {
        if (isInt(bookmarkIndexString)) {
            bookmarkIndex = Integer.parseInt(bookmarkIndexString);
            return true;
        } else {
            return false;
        }
    }

    public boolean setAnimeIndex(String animeIndexString) {
        if (isInt(animeIndexString)) {
            animeIndex = Integer.parseInt(animeIndexString);
            return true;
        } else {
            return false;
        }
    }

    public boolean setBookmarkEpisode(String bookmarkEpisodeString) {
        if (isInt(bookmarkEpisodeString)) {
            bookmarkEpisode = Integer.parseInt(bookmarkEpisodeString);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if String is a parsable int.
     *
     * @param checkStr string to check
     * @return true if parsable int
     */
    public boolean isInt(String checkStr) {
        return checkStr.matches("-?\\d+(\\.\\d+)?");
    }

}
