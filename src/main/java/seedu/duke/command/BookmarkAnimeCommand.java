package seedu.duke.command;

import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.human.Workspace;
import seedu.duke.parser.BookmarkParser;
import seedu.duke.storage.StorageManager;

import static seedu.duke.logger.AniLogger.getAniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BookmarkAnimeCommand extends Command {

    private int bookmarkIndex;
    private int animeIndex;
    private int bookmarkEpisode;
    private String bookmarkAction;
    private static final Logger LOGGER = getAniLogger(BookmarkAnimeCommand.class.getName());

    public BookmarkAnimeCommand() {
        // Set log levels
        // LOGGER.setLevel(Level.WARNING);
        LOGGER.log(Level.INFO, "Successfully loaded fields for Bookmark command.");
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        String result = "";
        Bookmark bookmark = user.getActiveWorkspace().bookmark;
        Workspace workspace = user.getActiveWorkspace();
        switch (bookmarkAction) {
        case BookmarkParser
                .EPISODE_PARAM:
            LOGGER.log(Level.INFO, "Executing Edit Episode.");
            result = editBookmarkEpisode(animeData,workspace);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser
                .ADD_PARAM:
            LOGGER.log(Level.INFO, "Executing Add Anime to Bookmark.");
            result = addBookmarkEntry(animeData, workspace);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser
                .DELETE_PARAM:
            LOGGER.log(Level.INFO, "Executing Delete Anime from Bookmark.");
            result = deleteBookmarkEntry(animeData, workspace);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser
                .LIST_PARAM:
            LOGGER.log(Level.INFO, "Executing List all anime in Bookmark.");
            result = "Listing all anime in bookmark:";
            String bookmarkList = user.getActiveWorkspace().getBookmarkListInString(animeData);
            result += bookmarkList;
            break;
        default:
            break;
        }
        LOGGER.log(Level.INFO, "Execute Bookmark command successful.");
        return result;
    }

    private String deleteBookmarkEntry(AnimeData animeData, Workspace workspace) throws AniException {
        String result;
        if (bookmarkIndex > workspace.getBookmarkSize() || bookmarkIndex <= 0) {
            String invalidBookmarkIndex = "Bookmark index " + bookmarkIndex + "provided is invalid."
                    + System.lineSeparator() + " Bookmark index is outside Bookmark range (too big or too small).";
            LOGGER.log(Level.WARNING, "Bookmark command execute failed:" + invalidBookmarkIndex);
            throw new AniException(invalidBookmarkIndex);
        }
        Anime animeToDelete = workspace.getAnimeFromBookmark(animeData, bookmarkIndex - 1);
        result = "Removing " + animeToDelete.getAnimeName() + "! :(";
        workspace.removeBookmarkEntry(bookmarkIndex - 1);
        return result;
    }


    private String addBookmarkEntry(AnimeData animeData, Workspace workspace) throws AniException {
        String result;
        if (animeIndex > animeData.getSize() || animeIndex <= 0) {
            String invalidAnimeIndex = "Anime index " + animeIndex + "provided is invalid."
                    + System.lineSeparator() + " Anime index is outside AnimeData range (too big or too small).";
            LOGGER.log(Level.WARNING, "Bookmark command execute failed:" + invalidAnimeIndex);
            throw new AniException(invalidAnimeIndex);
        }

        Anime animeToAdd = animeData.getAnimeByID(animeIndex - 1);
        result = "Saving " + animeToAdd.getAnimeID() + ". " + animeToAdd.getAnimeName() + " to bookmark.";
        workspace.addBookmarkEntry(animeIndex - 1);
        return result;
    }

    private String editBookmarkEpisode(AnimeData animeData, Workspace workspace) throws AniException {
        if (bookmarkIndex > workspace.getBookmarkSize() || bookmarkIndex <= 0) {
            String invalidBookmarkIndex = "Bookmark index " + bookmarkIndex + " provided is invalid."
                    + System.lineSeparator() + " Bookmark index is outside Bookmark range (too big or too small).";
            LOGGER.log(Level.WARNING, "Bookmark command execute failed:" + invalidBookmarkIndex);
            throw new AniException(invalidBookmarkIndex);
        }
        String result;
        assert bookmarkEpisode >= 0 : "bookmarkEpisode should be positive";
        workspace.editBookmarkEpisode(bookmarkIndex - 1, bookmarkEpisode);
        Anime animeToEdit = workspace.getAnimeFromBookmark(animeData, bookmarkIndex - 1);
        result = "Editing " + animeToEdit.getAnimeName() + " to have " + bookmarkEpisode + " episode";
        return result;
    }

    public String getBookmarkAction() {
        return this.bookmarkAction;
    }

    public void setBookmarkAction(String actionString) {
        this.bookmarkAction = actionString;
    }

    public void setBookmarkIndex(String bookmarkIndexString) {
        this.bookmarkIndex = Integer.parseInt(bookmarkIndexString);
    }

    public void setAnimeIndex(String animeIndexString) {
        this.animeIndex = Integer.parseInt(animeIndexString);
    }

    public void setBookmarkEpisode(String bookmarkEpisodeString) {
        this.bookmarkEpisode = Integer.parseInt(bookmarkEpisodeString);
    }
}
