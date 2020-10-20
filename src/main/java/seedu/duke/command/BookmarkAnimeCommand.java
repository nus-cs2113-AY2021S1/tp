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

    private static final String ANIME_ID_ERROR = " Anime index is outside AnimeData range (too big or too small).";
    private static final String BOOKMARK_ID_ERROR = " Bookmark index is outside Bookmark range (too big or too small).";
    private static final String BOOKMARK_EXECUTE_ERROR_HEADER = "Bookmark command execute failed:";
    private static final String BOOKMARK_ERROR_MESSAGE = "provided is invalid.";
    private static final String BOOKMARK_EXECUTE_EDIT = "Executing Edit Episode.";
    private static final String BOOKMARK_EXECUTE_ADD = "Executing Add Anime to Bookmark.";
    private static final String BOOKMARK_EXECUTE_DELETE = "Executing Delete Anime from Bookmark.";
    private static final String BOOKMARK_EXECUTE_LIST = "Executing List all anime in Bookmark.";
    private static final String BOOKMARK_EXECUTE_SUCCESS = "Execute Bookmark command successful.";
    private static final String BOOKMARK_LIST_HEADER = "Listing all anime in bookmark:";
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
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_EDIT);
            result = editBookmarkEpisode(animeData, workspace);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser
                .ADD_PARAM:
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_ADD);
            result = addBookmarkEntry(animeData, workspace);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser
                .DELETE_PARAM:
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_DELETE);
            result = deleteBookmarkEntry(animeData, workspace);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser
                .LIST_PARAM:
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_LIST);
            result = BOOKMARK_LIST_HEADER;
            String bookmarkList = user.getActiveWorkspace().getBookmarkListInString(animeData);
            result += bookmarkList;
            break;
        default:
            break;
        }
        LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_SUCCESS);
        return result;
    }

    private String deleteBookmarkEntry(AnimeData animeData, Workspace workspace) throws AniException {
        String result;
        if (bookmarkIndex > workspace.getBookmarkSize() || bookmarkIndex <= 0) {
            String invalidBookmarkIndex = "Bookmark index " + bookmarkIndex + BOOKMARK_ERROR_MESSAGE
                    + System.lineSeparator() + BOOKMARK_ID_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_EXECUTE_ERROR_HEADER + invalidBookmarkIndex);
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
            String invalidAnimeIndex = "Anime index " + animeIndex + BOOKMARK_ERROR_MESSAGE
                    + System.lineSeparator() + ANIME_ID_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_EXECUTE_ERROR_HEADER + invalidAnimeIndex);
            throw new AniException(invalidAnimeIndex);
        }

        Anime animeToAdd = animeData.getAnimeByID(animeIndex - 1);
        result = "Saving " + animeToAdd.getAnimeID() + ". " + animeToAdd.getAnimeName() + " to bookmark.";
        workspace.addBookmarkEntry(animeIndex - 1);
        return result;
    }

    private String editBookmarkEpisode(AnimeData animeData, Workspace workspace) throws AniException {
        if (bookmarkIndex > workspace.getBookmarkSize() || bookmarkIndex <= 0) {
            String invalidBookmarkIndex = "Bookmark index " + bookmarkIndex + BOOKMARK_ERROR_MESSAGE
                    + System.lineSeparator() + BOOKMARK_ID_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_EXECUTE_ERROR_HEADER + invalidBookmarkIndex);
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
