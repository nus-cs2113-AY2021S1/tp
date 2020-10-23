package anichan.command;

import anichan.human.User;
import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.bookmark.Bookmark;
import anichan.exception.AniException;
import anichan.human.Workspace;
import anichan.parser.BookmarkParser;
import anichan.storage.StorageManager;
import static anichan.logger.AniLogger.getAniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BookmarkAnimeCommand extends Command {

    private static final String ANIME_ID_ERROR = " Anime index is outside AnimeData range (too big or too small).";
    private static final String BOOKMARK_ID_ERROR = " Bookmark index is outside Bookmark range (too big or too small).";
    private static final String BOOKMARK_EXECUTE_ERROR_HEADER = "Bookmark command execute failed:";
    private static final String BOOKMARK_ERROR_MESSAGE = " provided is invalid.";
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
    private String bookmarkNote;
    private static final Logger LOGGER = getAniLogger(BookmarkAnimeCommand.class.getName());

    public BookmarkAnimeCommand() {
        // Set log levels
        // LOGGER.setLevel(Level.WARNING);
        bookmarkAction = null;
        LOGGER.log(Level.INFO, "Successfully loaded fields for Bookmark command.");
    }

    @Override
    public String execute(AnimeData animeData, StorageManager storageManager, User user) throws AniException {
        String result = "";
        Workspace workspace = user.getActiveWorkspace();
        Bookmark bookmark = Workspace.getBookmark();
        switch (bookmarkAction) {
        case BookmarkParser.EPISODE_PARAM:
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_EDIT);
            result = editBookmarkEpisode(animeData, bookmark);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser.ADD_PARAM:
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_ADD);
            result = addBookmarkEntry(animeData, workspace);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser.DELETE_PARAM:
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_DELETE);
            result = deleteBookmarkEntry(animeData, bookmark);
            storageManager.saveBookmark(workspace.getName(), bookmark);
            break;
        case BookmarkParser.LIST_PARAM:
            LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_LIST);
            result = BOOKMARK_LIST_HEADER;
            String bookmarkList = listBookmark(animeData, workspace);
            result += bookmarkList;
            break;
        case BookmarkParser.INFO_PARAM:
            LOGGER.log(Level.INFO, "Executing anime info for bookmark");
            result = "Here is the information for that anime." + System.lineSeparator();
            String animeInfo = getAnimeInfoFromBookmark(animeData, bookmark);
            result += animeInfo + System.lineSeparator() + System.lineSeparator();
            result += "Notes for anime:";
            String notesInfo = getAnimeNotesFromBookmark(workspace);
            result += notesInfo;
            break;
        case BookmarkParser.ADD_NOTE_PARAM:
            LOGGER.log(Level.INFO, "Executing bookmark add note");
            result = addNoteToBookmark(animeData, bookmark);
            break;
        default:
            break;
        }
        LOGGER.log(Level.INFO, BOOKMARK_EXECUTE_SUCCESS);
        return result;
    }

    private String getAnimeNotesFromBookmark(Workspace bookmark) {
        String notesInfo = bookmark.getBookmarkNote(bookmarkIndex - 1);
        return notesInfo;
    }

    private String addNoteToBookmark(AnimeData animeData, Bookmark bookmark) throws AniException {
        String result;
        checkBookmarkIndex(bookmark);
        bookmark.addNote(bookmarkIndex - 1, bookmarkNote);
        Anime animeToDelete = bookmark.getAnimeBookmarkByIndex(animeData, bookmarkIndex - 1);
        result = "Adding note:\"" + bookmarkNote + "\" to " + animeToDelete.getAnimeName() + "!";
        return result;
    }

    private String getAnimeInfoFromBookmark(AnimeData animeData, Bookmark bookmark) throws AniException {
        checkBookmarkIndex(bookmark);
        String animeInfo = bookmark.getAnimeBookmarkInfo(animeData, bookmarkIndex - 1);
        return animeInfo;
    }

    private String deleteBookmarkEntry(AnimeData animeData, Bookmark bookmark) throws AniException {
        checkBookmarkIndex(bookmark);
        String result;
        Anime animeToDelete = bookmark.getAnimeBookmarkByIndex(animeData, bookmarkIndex - 1);
        result = "Removing " + animeToDelete.getAnimeName() + "! :(";
        bookmark.removeAnimeBookmark(bookmarkIndex - 1);
        return result;
    }


    private String addBookmarkEntry(AnimeData animeData, Workspace workspace) throws AniException {
        checkAnimeIndex(animeData);
        String result;
        Anime animeToAdd = animeData.getAnime(animeIndex - 1);
        result = "Saving " + animeToAdd.getAnimeID() + ". " + animeToAdd.getAnimeName() + " to bookmark.";
        workspace.addBookmarkEntry(animeIndex - 1);
        return result;
    }

    private String editBookmarkEpisode(AnimeData animeData, Bookmark bookmark) throws AniException {
        checkBookmarkIndex(bookmark);
        String result;
        bookmark.editAnimeBookmarkEpisode(bookmarkIndex - 1, bookmarkEpisode);
        Anime animeToEdit = bookmark.getAnimeBookmarkByIndex(animeData, bookmarkIndex - 1);
        result = "Editing " + animeToEdit.getAnimeName() + " to have " + bookmarkEpisode + " episode";
        return result;
    }

    private void checkBookmarkIndex(Bookmark bookmark) throws AniException {
        //Bookmark index is one based numbering
        if (bookmarkIndex > bookmark.getBookmarkSize() || bookmarkIndex <= 0) {
            String invalidBookmarkIndex = "Bookmark index " + bookmarkIndex + BOOKMARK_ERROR_MESSAGE
                    + System.lineSeparator() + BOOKMARK_ID_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_EXECUTE_ERROR_HEADER + invalidBookmarkIndex);
            throw new AniException(invalidBookmarkIndex);
        }
    }

    private void checkAnimeIndex(AnimeData animeData) throws AniException {
        //Anime index is one based numbering
        if (animeIndex > animeData.getSize() || animeIndex <= 0) {
            String invalidAnimeIndex = "Anime index " + animeIndex + BOOKMARK_ERROR_MESSAGE
                    + System.lineSeparator() + ANIME_ID_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_EXECUTE_ERROR_HEADER + invalidAnimeIndex);
            throw new AniException(invalidAnimeIndex);
        }
    }

    private String listBookmark(AnimeData animeData, Workspace workspace) throws AniException {
        return workspace.getBookmarkListInString(animeData);
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

    public void setBookmarkNote(String note) {
        this.bookmarkNote = note;
    }
}
